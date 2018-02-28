package com.example.module_b;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.module_c.Calculate;
import com.example.my_annotation.AutoWire;
import com.example.my_annotation.Module;

import java.lang.reflect.Method;


/**
 * Created by xinmei on 2018/1/25.
 */
@Module
public class TargetOperationClass {

    Object object;
    int num = 0;
    Object object2;
    public TargetOperationClass() {
        /*try {
            Class utilClass = Class.forName("com.example.xinmei.routerdemo.Util");
            Method utilMethod = utilClass.getMethod("getCalculator", new Class[0]);
            this.object = utilMethod.invoke(null, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
    public TargetOperationClass(Context context){

       /* try {
            Class utilClass = Class.forName("com.example.xinmei.routerdemo.Util");
            Method utilMethod = utilClass.getMethod("getCalculator", new Class[0]);
            this.object = utilMethod.invoke(null, new Object[]{});

            Class mUtilClass = Class.forName("com.example.xinmei.routerdemo.Util");
            Method mUtilMethod = mUtilClass.getMethod("getContextTest", Context.class);
            System.out.println("context in module b is :"+context);
            this.object2=mUtilMethod.invoke(null,context);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }
    public void testMethod1() {

        System.out.println("This is testMethod1");
    }

    @AutoWire
    public void operation() throws IllegalAccessException, InstantiationException {

        System.out.println("===========object:" + object);
        System.out.println("===========object1:"+ object2);
        System.out.println("Over");
    }
}
