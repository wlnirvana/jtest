package com.wlnirvana.jtest;

import org.junit.jupiter.api.Test;

import static org.joor.Reflect.on;
import static org.joor.Reflect.onClass;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ALL")
class T6 {

    /**
     * This is a test class used in {@link T6}
     */
    static class ClassD {
        @JTest
        public static void test1Pass() {
        }

        @JTest
        public static void test2Pass() {
            JAssertions.assertTrue(2 > 1);
        }

        @JTest
        public static void test3Fail() {
            JAssertions.assertEquals("hello", "world");
        }

        @JTest
        public static void test4Error() {
            int[] arr = new int[0];
            arr[5] = 99;
        }
    }

    /**
     * Check tests that should pass
     */
    @Test
    void testPassResult() throws NoSuchMethodException {
        var res1 = onClass("com.wlnirvana.jtest.JTestRunner")
                .call("runMethod", T6.ClassD.class.getMethod("test1Pass")).get();
        var actualMethodName1 = on(res1).call("getTestMethodName").get();
        var actualStatus1 = on(res1).call("getTestStatus").get();
        assertEquals("test1Pass", actualMethodName1);
        assertEquals("PASS", actualStatus1);

        var res2 = onClass("com.wlnirvana.jtest.JTestRunner")
                .call("runMethod", T6.ClassD.class.getMethod("test2Pass")).get();
        var actualMethodName2 = on(res2).call("getTestMethodName").get();
        var actualStatus2 = on(res2).call("getTestStatus").get();
        assertEquals("test2Pass", actualMethodName2);
        assertEquals("PASS", actualStatus2);
    }

    /**
     * Check tests that should fail
     */
    @Test
    void testFailedResult() throws NoSuchMethodException {
        var res = onClass("com.wlnirvana.jtest.JTestRunner")
                .call("runMethod", T6.ClassD.class.getMethod("test3Fail")).get();
        var actualMethodName1 = on(res).call("getTestMethodName").get();
        var actualStatus1 = on(res).call("getTestStatus").get();
        assertEquals("test3Fail", actualMethodName1);
        assertEquals("FAIL", actualStatus1);
    }


    /**
     * Check tests that should error out
     */
    @Test
    void testErrorResult() throws NoSuchMethodException {
        var res = onClass("com.wlnirvana.jtest.JTestRunner")
                .call("runMethod", T6.ClassD.class.getMethod("test4Error")).get();
        var actualMethodName1 = on(res).call("getTestMethodName").get();
        var actualStatus1 = on(res).call("getTestStatus").get();
        assertEquals("test4Error", actualMethodName1);
        assertEquals("ERROR", actualStatus1);
    }

}