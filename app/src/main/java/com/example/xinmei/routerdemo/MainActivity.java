package com.example.xinmei.routerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {
    private static MainActivity main=new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("11111111");
        try {
            main.test();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("222222222");

    }

    public void test() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ClassTest classTest = new ClassTest();
        classTest.myTest();

    }

}

