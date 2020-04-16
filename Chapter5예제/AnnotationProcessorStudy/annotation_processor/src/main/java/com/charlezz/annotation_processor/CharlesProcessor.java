package com.charlezz.annotation_processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.charlezz.annotation.CharlesIntent;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

@AutoService(Processor.class)
public class CharlesProcessor extends AbstractProcessor {

    private static final ClassName intentClass = ClassName.get("android.content", "Intent");
    private static final ClassName contextClass = ClassName.get("android.content", "Context");
    private static final String METHOD_PREFIX_NEW_INTENT = "intentFor";

    ArrayList<MethodSpec> newIntentMethodSpecs = new ArrayList<>();

    private String packageName;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        System.out.println("process");

        final Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(CharlesIntent.class);

        for (Element element : elements) {
            if(packageName==null){
                Element e = element;
                while (!(e instanceof PackageElement)) {
                    e = e.getEnclosingElement();
                }
                packageName = ((PackageElement)e).getQualifiedName().toString();
            }

            if (element.getKind() != ElementKind.CLASS) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "CharlesIntent can only use for classes!");
                return false;
            }
            newIntentMethodSpecs.add(generateMethod((TypeElement) element));
        }

        if (roundEnvironment.processingOver()) {
            try {
                generateJavaFile(newIntentMethodSpecs);
                return true;
            } catch (IOException ex) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, ex.toString());
            }
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<String>(){
            {
                add(CharlesIntent.class.getCanonicalName());
            }
        };
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private MethodSpec generateMethod(TypeElement element) {
        return MethodSpec
                .methodBuilder(METHOD_PREFIX_NEW_INTENT + element.getSimpleName())
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(contextClass, "context")
                .returns(intentClass)
                .addStatement("return new $T($L, $L)", intentClass, "context", element.getQualifiedName() + ".class")
                .build();
    }

    private void generateJavaFile(List<MethodSpec> methodSpecList) throws IOException {
        System.out.println("methodSpecList Count = "+methodSpecList.size());
        final TypeSpec.Builder builder = TypeSpec.classBuilder("Charles");
        builder.addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        for (MethodSpec methodSpec : methodSpecList) {
            builder.addMethod(methodSpec);
        }

        final TypeSpec typeSpec = builder.build();

        JavaFile.builder(packageName, typeSpec)
                .build()
                .writeTo(processingEnv.getFiler());
    }
}