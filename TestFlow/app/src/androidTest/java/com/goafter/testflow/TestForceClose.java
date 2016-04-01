package com.goafter.testflow;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.test.InstrumentationTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by huashuolee on 2016/3/28.
 */

public class TestForceClose extends InstrumentationTestCase{
    UiDevice device;
    @Before
    public void setUp(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        final UiObject fc = device.findObject(new UiSelector().textContains("很抱歉"));
        UiWatcher watcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                if (fc.exists()){
                    try {
                        device.findObject(new UiSelector().textContains("确定")).click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }}
                return true;
            }
        };

        device.getInstance(getInstrumentation()).registerWatcher("fc", watcher);
        device.getInstance(getInstrumentation()).runWatchers();
    }


    @Test
    public void test_als() throws UiObjectNotFoundException,InterruptedException{
        UiObject carInfo = device.findObject(new UiSelector().resourceId("com.ucs:id/desk_tab_carinfo"));
        carInfo.click();
        Thread.sleep(2);
        device.findObject(new UiSelector().resourceId("com.ucs:id/already_sailing")).click();
        Thread.sleep(2);
        for (int i =0; i<5; i++) {
            device.swipe(640, 1076, 640, 420, 5);
            Thread.sleep(1000);
        }
        Thread.sleep(2);
        device.click(640, 1076);
        device.click(640, 1076);
        UiObject share = device.findObject(new UiSelector().resourceId("com.ucs:id/goto_share"));
        share.click();




    }

}
