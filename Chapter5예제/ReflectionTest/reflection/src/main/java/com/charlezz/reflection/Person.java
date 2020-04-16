package com.charlezz.reflection;

import android.util.Log;

public class Person {

    public static final String TAG = Person.class.getSimpleName();

    String name;
    int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void sayMyName(){
        Log.e(TAG,String.format("Hello! My name is %s and I'm %d years old",name, age) );
    }
}
