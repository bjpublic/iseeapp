package com.charlezz.reflection;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReflectionTest r = new ReflectionTest();
        r.changeValuesOfFields();
    }
}
