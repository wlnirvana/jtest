package com.wlnirvana.jtest;

import org.junit.jupiter.api.Test;

import static org.joor.Reflect.onClass;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ALL")
class T5 {

    /**
     * Check {@link JTestResult} has the correct method signatures
     */
    @Test
    void testJTestResultMethods() {
        Class<?> clazz = onClass("com.wlnirvana.jtest.JTestResult").get();
        assertTrue(hasDeclaredMethod(clazz, "getTestStatus", String.class));
        assertTrue(hasDeclaredMethod(clazz, "getTestMethodName", String.class));
    }

    /**
     * Check if a class has method with specified signature
     *
     * @param clazz              the fully qualified name of the class
     * @param methodName         method name
     * @param expectedReturnType return type
     * @param expectedArgTypes   an array of arg types
     * @return the method
     */
    private static boolean hasDeclaredMethod(Class<?> clazz, String methodName, Class<?> expectedReturnType, Class<?>... expectedArgTypes) {
        try {
            var method = clazz.getDeclaredMethod(methodName, expectedArgTypes);
            return expectedReturnType.isAssignableFrom(method.getReturnType());
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

}