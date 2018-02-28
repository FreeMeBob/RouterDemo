package com.example.xinmei.routerdemo;

import android.content.Context;

import com.example.module_c.Calculate;
import com.example.module_c.ContextTest;

/**
 * Created by xinmei on 2018/1/25.
 */

public class Util {

    public static Object getCalculator() {
        return  new Calculate();
    }
    public static Object getContextTest(Context context){return new ContextTest(context);}
}
