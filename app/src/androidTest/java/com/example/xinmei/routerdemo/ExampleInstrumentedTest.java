package com.example.xinmei.routerdemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.module_c.Parse;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.xinmei.routerdemo", appContext.getPackageName());
    }

    @Test
    public void testXmlParase(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        System.out.println("222222222");
        Parse parse=new Parse(appContext);
        parse.parseXML();
        System.out.println("5555555");

    }
}
