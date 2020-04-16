package com.charlezz.checker;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.intellij.psi.PsiMethod;

public class SetContentViewDetector
        extends Detector
        implements SourceCodeScanner {

    public static final Issue ISSUE = Issue.create(
            SetContentViewDetector.class.getSimpleName(),
            "Prohibits usages of setContentView()",
            "Prohibits usages of setContentView(), use DataBindingUtil.setContentView() instead",
            Category.CORRECTNESS,
            5,
            Severity.ERROR,
            new Implementation(SetContentViewDetector.class, Scope.JAVA_FILE_SCOPE)
    );

    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("setContentView");
    }

    @Override
    public void visitMethodCall(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull PsiMethod method) {

        if (context.getEvaluator().isMemberInClass(method, "androidx.databinding.DataBindingUtil")) {
            return;
        }

        context.report(
                ISSUE,
                node,
                context.getLocation(node),
                "Use DataBindingUtil.setContentView() instead"
        );
    }

}
