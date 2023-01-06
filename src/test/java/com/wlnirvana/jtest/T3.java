package com.wlnirvana.jtest;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.joor.Reflect.onClass;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ALL")
class T3 {

    /**
     * This is a test class used in {@link T3}
     */
    static class ClassC {
        static int a = 1;
        static double b = 3.0;

        @JTest
        public static void test1() {
            a++;
        }

        @JTest
        public static void test2() {
            b = b * b;
        }

        public static void notTest() {
            a = 0;
            b = 0;
        }
    }

    /**
     * Check if {@link JTestRunner#runMethod(Method)} can invoke the (static)
     * method passed in correctly.
     * <p>
     * Note that the test class contains a method {@link ClassC#notTest()} not
     * annotated by {@link JTest}, but is also passed in as argument to
     * {@link JTestRunner#runMethod(Method)} in this test case. It should be
     * invoked as well.
     */
    @Test
    void findTestMethodsInClassA() throws NoSuchMethodException {
        assertEquals(1, ClassC.a);
        onClass("com.wlnirvana.jtest.JTestRunner").call("runMethod", ClassC.class.getMethod("test1"));
        assertEquals(2, ClassC.a);
        onClass("com.wlnirvana.jtest.JTestRunner").call("runMethod", ClassC.class.getMethod("test1"));
        assertEquals(3, ClassC.a);

        assertEquals(3.0, ClassC.b);
        onClass("com.wlnirvana.jtest.JTestRunner").call("runMethod", ClassC.class.getMethod("test2"));
        assertEquals(9.0, ClassC.b);

        onClass("com.wlnirvana.jtest.JTestRunner").call("runMethod", ClassC.class.getMethod("notTest"));
        assertEquals(0, ClassC.a);
        assertEquals(0.0, ClassC.b);
    }

}