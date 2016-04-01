package com.goafter.testflow;


import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.test.InstrumentationTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by huashuolee on 2016/3/14.
 */
/*
public class MainActivityUiAutomator extends UiAutomatorTestCase {

    public void testDemo() throws Exception{
        UiDevice device = getUiDevice();
        device.sleep();
        sleep(10);
        device.wakeUp();
        assertTrue("The screen On",device.isScreenOn());
        device.pressHome();
    }
}
*/



@RunWith(AndroidJUnit4.class)
public class MainActivityUiAutomator extends InstrumentationTestCase {
    UiDevice device;


    @Before
    public void setUp() throws Exception {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.sleep();
        Thread.sleep(2);
        device.wakeUp();
        Thread.sleep(2);
        device.drag(500, 1070, 500, 220, 10);
        final UiObject deskTop = device.findObject(new UiSelector().resourceId("com.ucs:id/desk_tab_desk"));
        UiWatcher watcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                try {
                    deskTop.click();
                } catch (UiObjectNotFoundException e) {
                    e.printStackTrace();
                }

                return false;
            }
        };
        device.getInstance(getInstrumentation()).registerWatcher("test", watcher);
        device.getInstance(getInstrumentation()).runWatchers();
    }

    @Test
    public void test1() throws Exception {
        final UiObject deskTop = device.findObject(new UiSelector().resourceId("com.ucs:id/desk_tab_desk"));
        //deskTop.click();
        //assertTrue("not true",false);
    }

/*    @Test
    public void testDemo() throws Exception {
        device = UiDevice.getInstance((InstrumentationRegistry.getInstrumentation()));
        device.sleep();
        Thread.sleep(5);
        device.wakeUp();
        Thread.sleep(2);
        device.drag(500, 1070, 500, 220, 10);

        UiWatcher watcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject deskTop = device.findObject(new UiSelector().resourceId("com.ucs:id/desk_tab_desk"));
                if (deskTop.exists()) {
                    try {
                        deskTop.click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                return false;
            }
        };
        device.getInstance(getInstrumentation()).registerWatcher("test", watcher);
        device.getInstance(getInstrumentation()).runWatchers();


    }*/


/*    @Test
    public void test_FC() throws Exception {
        UiObject deskTop = device.findObject(new UiSelector().resourceId("com.ucs:id/desk_tab_desk"));
        //UiObject  deskTop = device.findObject(new UiSelector().textContains("工作台"));
        //deskTop.click();
        System.err.println(deskTop.exists());
        //assertTrue(deskTop.exists());

    }*/

}