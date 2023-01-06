package com.wlnirvana.jtest;

import com.github.blindpirate.extensions.CaptureSystemOutput;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.joor.Reflect.onClass;

@SuppressWarnings("ALL")
class T8 {

    /**
     * This is a test class used in {@link T8#testClassG(CaptureSystemOutput.OutputCapture)}
     */
    static class ClassG {

        /**
         * A test that should pass
         */
        @JTest
        public static void assertEqualsPass() {
            Integer a = 999;
            Integer b = 999;
            JAssertions.assertEquals(a, b);
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
    }

    /**
     * Run test class {@link ClassG}
     */
    @Test
    @CaptureSystemOutput
    void testClassG(CaptureSystemOutput.OutputCapture capture) {
        String[] args = {ClassG.class.getName()};
        onClass("com.wlnirvana.jtest.JTestRunner").call("main", (Object) args);
        capture.expect(Matchers.containsString("2/3 have passed"));
        capture.expect(Matchers.containsString("Failed tests are: []"));
        capture.expect(Matchers.containsString("Errored tests are: [error]"));
    }
}