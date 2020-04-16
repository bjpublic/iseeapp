package com.charlezz.reflection;

import android.util.Log;

public class A {
    public static final String TAG = A.class.getSimpleName();

    private void show() {
        Log.e(TAG, "Hello I am private method");
    }
}
