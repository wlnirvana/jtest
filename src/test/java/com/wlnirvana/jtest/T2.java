package com.wlnirvana.jtest;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.joor.Reflect.onClass;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ALL")
class T2 {

    /**
     * This is a test class used in {@link T2#findTestMethodsInClassA()}
     */
    static class ClassA {
        @JTest
        public void test1() {
        }

        @JTest
        public void test2() {
        }

        public void notTest() {
        }
    }


    /**
     * Check if {@link JTest} annotated methods in {@link ClassA} can be found correctly
     */
    @Test
    void findTestMethodsInClassA() {
        List<Method> testMethodsInClassA = onClass("com.wlnirvana.jtest.JTestRunner").call("getTestMethods", ClassA.class).get();
        var actualMethodNames = testMethodsInClassA.stream().map(Method::getName).collect(Collectors.toSet());
        var expectedMethodNames = Set.of("test1", "test2");
        assertEquals(expectedMethodNames, actualMethodNames);
    }

    /**
     * This is a test class used in {@link T2#findTestMethodsInClassB()}
     */
    static class ClassB {
        @JTest
        public void test3() {
        }
    }

    /**
     * Check if {@link JTest} annotated methods in {@link ClassB} can be found correctly
     */
    @Test
    void findTestMethodsInClassB() {
        List<Method> testMethodsInClassB = onClass("com.wlnirvana.jtest.JTestRunner").call("getTestMethods", ClassB.class).get();
        var actualMethodNames = testMethodsInClassB.stream().map(Method::getName).collect(Collectors.toSet());
        var expectedMethodNames = Set.of("test3");
        assertEquals(expectedMethodNames, actualMethodNames);
    }

}