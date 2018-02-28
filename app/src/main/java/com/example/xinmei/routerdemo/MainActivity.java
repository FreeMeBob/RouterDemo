package com.example.xinmei.routerdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.module_b.TargetOperationClass;
import com.example.module_c.ContextTest;
import com.example.module_c.Parse;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private static MainActivity main=new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("11111111");
        context=this.getApplicationContext();
        System.out.println("---- context:"+context);
        /*try {
            main.test();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
        TargetOperationClass targetOperationClass=new TargetOperationClass(context);
        targetOperationClass.testMethod1();
        try {
            targetOperationClass.operation();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("e1-------->"+e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println("e2-------->"+e);
        }
        ContextTest contextTest=new ContextTest(context);
        contextTest.isWorkable();
    }

    public void test() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ClassTest classTest = new ClassTest();
        classTest.myTest(context);

    }

}

