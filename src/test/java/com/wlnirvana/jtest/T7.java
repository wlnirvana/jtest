package com.wlnirvana.jtest;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.joor.Reflect.on;
import static org.joor.Reflect.onClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings("ALL")
class T7 {

    /**
     * This is a test class used in {@link T7#testClassE()}
     */
    static class ClassE {

        /**
         * A test that should fail (on most JVMs)
         */
        @JTest
        public static void assertEqualsFail() {
            String s1 = new String("hello");
            String s2 = new String("world");
            JAssertions.assertEquals(s1, s2);
        }

        /**
         * A test that should pass
         */
        @JTest
        public static void assertTruePass() {
            JAssertions.assertTrue(3 > 2);
        }

        /**
         * A test that should error out
         */
        @JTest
        public static void error() {
            int a = 9;
            int b = a / 0;
            JAssertions.assertTrue(3 > 2);
        }

        /**
         * A method not annotated by {@link JTest}
         */
        public static void noTest() {
            JAssertions.assertTrue(3 > 2);
        }

    }

    /**
     * Run test class {@link ClassE}
     */
    @Test
    void testClassE() {
        List<?> results = onClass("com.wlnirvana.jtest.JTestRunner")
                .call("runTestClass", ClassE.class.getName())
                .get();
        assertEquals(3, results.size());

        Object result;

        // passed
        result = results.stream()
                .filter(r -> on(r).call("getTestStatus").get().equals("PASS"))
                .findFirst()
                .orElse(null);
        assertNotNull(result);
        assertEquals("assertTruePass", on(result).call("getTestMethodName").get());

        // failed
        result = results.stream()
                .filter(r -> on(r).call("getTestStatus").get().equals("FAIL"))
                .findFirst()
                .orElse(null);
        assertNotNull(result);
        assertEquals("assertEqualsFail", on(result).call("getTestMethodName").get());

        // error
        result = results.stream()
                .filter(r -> on(r).call("getTestStatus").get().equals("ERROR"))
                .findFirst()
                .orElse(null);
        assertNotNull(result);
        assertEquals("error", on(result).call("getTestMethodName").get());
    }

    /**
     * This is a test class used in {@link T7#testClassF()}
     */
    static class ClassF {

        /**
         * A test that should pass
         */
        @JTest
        public static void assertEqualsPass() {
            String s1 = new String("hello");
            String s2 = new String("hello");
            JAssertions.assertEquals(s1, s2);
        }

        /**
         * A test that should fail
         */
        @JTest
        public static void assertTrueFail() {
            JAssertions.assertTrue(1 > 2);
        }

    }

    /**
     * 验证测试类{@link ClassF}
     */
    @Test
    void testClassF() {
        List<?> results = onClass("com.wlnirvana.jtest.JTestRunner")
                .call("runTestClass", ClassF.class.getName())
                .get();
        assertEquals(2, results.size());

        Object result;

        // passed
        result = results.stream()
                .filter(r -> on(r).call("getTestStatus").get().equals("PASS"))
                .findFirst()
                .orElse(null);
        assertNotNull(result);
        assertEquals("assertEqualsPass", on(result).call("getTestMethodName").get());

        // failed
        result = results.stream()
                .filter(r -> on(r).call("getTestStatus").get().equals("FAIL"))
                .findFirst()
                .orElse(null);
        assertNotNull(result);
        assertEquals("assertTrueFail", on(result).call("getTestMethodName").get());
    }

}