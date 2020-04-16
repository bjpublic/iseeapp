package com.charlezz.dagger2.dagger.test.case08;

import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;

public class Foo3 {

    @Inject
    Set<String> strings;

    public void print() {
        for (Iterator itr = strings.iterator(); itr.hasNext(); ) {
            System.out.println(itr.next());
        }
    }

}