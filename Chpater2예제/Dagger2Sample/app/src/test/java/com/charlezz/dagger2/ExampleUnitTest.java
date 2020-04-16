package com.charlezz.dagger2;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.charlezz.dagger2.dagger.test.case01.DaggerMyComponent;
import com.charlezz.dagger2.dagger.test.case01.MyClass;
import com.charlezz.dagger2.dagger.test.case01.MyComponent;
import com.charlezz.dagger2.dagger.test.case03.DaggerMyComponent2;
import com.charlezz.dagger2.dagger.test.case03.MyComponent2;
import com.charlezz.dagger2.dagger.test.case03.Person;
import com.charlezz.dagger2.dagger.test.case04.DaggerPersonComponent;
import com.charlezz.dagger2.dagger.test.case04.PersonA;
import com.charlezz.dagger2.dagger.test.case04.PersonB;
import com.charlezz.dagger2.dagger.test.case04.PersonComponent;
import com.charlezz.dagger2.dagger.test.case05.Counter;
import com.charlezz.dagger2.dagger.test.case05.CounterComponent;
import com.charlezz.dagger2.dagger.test.case05.DaggerCounterComponent;
import com.charlezz.dagger2.dagger.test.case06.DaggerNoStrComponent;
import com.charlezz.dagger2.dagger.test.case06.DaggerStrComponent;
import com.charlezz.dagger2.dagger.test.case06.Foo;
import com.charlezz.dagger2.dagger.test.case07.BindsComponent;
import com.charlezz.dagger2.dagger.test.case07.DaggerBindsComponent;
import com.charlezz.dagger2.dagger.test.case07.Foo2;
import com.charlezz.dagger2.dagger.test.case08.DaggerSetComponent;
import com.charlezz.dagger2.dagger.test.case08.Foo3;
import com.charlezz.dagger2.dagger.test.case09.DaggerMapComponent;
import com.charlezz.dagger2.dagger.test.case09.MapComponent;
import com.charlezz.dagger2.dagger.test.case10.Animal;
import com.charlezz.dagger2.dagger.test.case10.DaggerMapKeyComponent;
import com.charlezz.dagger2.dagger.test.case10.MapKeyComponent;
import com.charlezz.dagger2.dagger.test.case11.ChildComponent;
import com.charlezz.dagger2.dagger.test.case11.DaggerParentComponent;
import com.charlezz.dagger2.dagger.test.case11.ParentComponent;
import com.charlezz.dagger2.dagger.test.case12.DaggerMultibindsComponent;
import com.charlezz.dagger2.dagger.test.case12.MultibindsComponent;
import com.charlezz.dagger2.dagger.test.case13.ComponentA;
import com.charlezz.dagger2.dagger.test.case13.ComponentB;
import com.charlezz.dagger2.dagger.test.case13.DaggerComponentA;
import com.charlezz.dagger2.dagger.test.case13.DaggerComponentB;
import com.charlezz.dagger2.dagger.test.case13.Foo4;


public class ExampleUnitTest {
    @Test
    public void testHelloWorld() {
        MyComponent myComponent = DaggerMyComponent.create();
        System.out.println("result = " + myComponent.getString());
    }

    @Test
    public void testMyComponent2() {
        MyComponent2 myComponent2 = DaggerMyComponent2.create();
        Person person = myComponent2.getPerson();
        Assert.assertNotNull(person);
        Assert.assertEquals(person.getName(), "Charles");
        Assert.assertEquals(person.getAge(), 100);
    }

    @Test
    public void testNullable() {
        MyComponent myComponent = DaggerMyComponent.create();
        Assert.assertNull(myComponent.getInteger());
    }

    @Test
    public void testMemberInjection() {
        MyClass myClass = new MyClass();
        String str = myClass.getStr();
        Assert.assertNull(str);
        MyComponent myComponent = DaggerMyComponent.create();
        myComponent.inject(myClass);
        Assert.assertEquals("Hello World", myClass.getStr());
    }

    @Test
    public void testInjection() {
        PersonComponent personComponent = DaggerPersonComponent.create();
        PersonA personA = personComponent.getPersonA();
        PersonB personB = new PersonB();
        DaggerPersonComponent.create().inject(personB);
        Assert.assertEquals("Charles", personA.getName());
        Assert.assertEquals("Charles", personB.getName());

        Assert.assertEquals(100, personA.getAge());
        Assert.assertEquals(100, personB.getAge());

    }

    @Test
    public void testLazy() {
        CounterComponent component = DaggerCounterComponent.create();
        Counter counter = new Counter();
        component.inject(counter);
        counter.printLazy();
    }

    @Test
    public void testProvider() {
        CounterComponent component = DaggerCounterComponent.create();
        Counter counter = new Counter();
        component.inject(counter);
        counter.printProvider();
    }

    @Test
    public void testMyComponentWithNamed() {
        MyClass myClass = new MyClass();
        DaggerMyComponent.create().inject(myClass);
        Assert.assertEquals("Hello", myClass.getStrHello());
        Assert.assertEquals("World", myClass.getStrWorld());
    }

    @Test
    public void testHelloWithQualifier() {
        MyClass myClass = new MyClass();
        DaggerMyComponent.create().inject(myClass);
        Assert.assertEquals(myClass.getStrHelloWithQualifier(), "Hello");
    }

    @Test
    public void testObjectIdentity() {
        MyComponent myComponent = DaggerMyComponent.create();
        Object temp1 = myComponent.getObject();
        Object temp2 = myComponent.getObject();
        System.out.println(temp1.hashCode());
        System.out.println(temp2.hashCode());
        System.out.println(temp1 == temp2);
        Assert.assertEquals(temp1, temp2);
    }

    @Test
    public void testFoo() {
        Foo foo = new Foo();
        DaggerStrComponent.create().inject(foo);
        Assert.assertTrue(foo.str.isPresent());
        Assert.assertNotNull(foo.str.get());

        DaggerNoStrComponent.create().inject(foo);
        Assert.assertFalse(foo.str.isPresent());
        //foo.str.get(); 호출시 NoSuchElementException 발생

    }

    @Test
    public void testBindsInstance() {
        String hello = "Hello World";
        Foo2 foo2 = new Foo2();
        BindsComponent component = DaggerBindsComponent.builder()
                .setString(hello)
                .build();
        component.inject(foo2);
        System.out.println(foo2.str);

    }

    @Test
    public void testMultibindingSet() {
        Foo3 foo3 = new Foo3();
        DaggerSetComponent.create().inject(foo3);
        foo3.print();

    }

    @Test
    public void testMultibindingMap() {
        MapComponent component = DaggerMapComponent.create();
        long value = component.getLongsByString().get("foo");
        String str = component.getStringsByClass().get(Foo.class);
        Assert.assertEquals(100, value);
        Assert.assertEquals("Foo String", str);
    }

    @Test
    public void testCustomMapKey() {
        MapKeyComponent component = DaggerMapKeyComponent.create();
        String cat = component.getStringsByAnimal().get(Animal.CAT);
        String dog = component.getStringsByAnimal().get(Animal.DOG);
        String number = component.getStringsByNumber().get(Float.class);
        Assert.assertEquals("Meow", cat);
        Assert.assertEquals("Bow-wow", dog);
        Assert.assertEquals("100f", number);
    }

    @Test
    public void testMultibindingWithSubcomponent() {
        ParentComponent parentComp = DaggerParentComponent.create();
        ChildComponent childComp = parentComp.childCompBuilder().build();
        System.out.println("List set in Parent");
        Iterator itr = parentComp.strings().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println("List set in Child");
        itr = childComp.strings().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    @Test
    public void testAbstractMultibinds() {
        MultibindsComponent component = DaggerMultibindsComponent.create();
        //비어 있음
        for (String s : component.getStrings()) {
            System.out.println(s);
        }
        Assert.assertEquals(0, component.getStrings().size());
    }

    @Test
    public void testDependency() {
        Foo4 foo4 = new Foo4();
        ComponentA componentA = DaggerComponentA.create();
        ComponentB componentB = DaggerComponentB.builder()
                .componentA(componentA)
                .build();
        componentB.inject(foo4);
        Assert.assertEquals("String from ModuleA", foo4.str);
        Assert.assertEquals(new Integer(100), foo4.integer);
    }

}


