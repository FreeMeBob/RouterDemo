package com.example.xinmei.routerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void test() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ClassTest classTest=new ClassTest();
        classTest.myTest();

    }

}
