package com.goafter.demologutil;

import android.test.InstrumentationTestCase;

/**
 * Created by huashuolee on 2015/12/2.
 */
public class DemoTest extends InstrumentationTestCase {
    public void test(){
        final int expected = 1;
        final int actual = 2;
        assertEquals(expected,actual);
    }
}
