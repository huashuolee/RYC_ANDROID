package com.goafter.testflow;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.test.InstrumentationTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by huashuolee on 2016/3/28.
 */

@RunWith(AndroidJUnit4.class)
public class AnotherTest extends InstrumentationTestCase {
    UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    @Test
    public void test_1() {

        UiWatcher watcher = new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject fc = device.findObject(new UiSelector().textContains("很抱歉"));
                if (fc.exists()) {
                    try {
                        device.findObject(new UiSelector().textContains("确定")).click();
                    } catch (UiObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return true;

            }
        };
        device.getInstance(getInstrumentation()).registerWatcher("fc",watcher);
        device.getInstance(getInstrumentation()).runWatchers();
    }
}



