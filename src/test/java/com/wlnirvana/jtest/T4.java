package com.wlnirvana.jtest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("ALL")
class T4 {

    /**
     * Check {@link JAssertions#assertTrue(boolean)} works
     */
    @Test
    void assertTrueWorks() {
        JAssertions.assertTrue(2 > 1);
        assertThrows(AssertionError.class, () -> JAssertions.assertTrue(1 > 2));
    }

    /**
     * Check {@link JAssertions#assertEquals(Object, Object)} works
     */
    @Test
    void assertEqualsWorks() {
        String s1 = new String("hello");
        String s2 = new String("hello");
        JAssertions.assertEquals(s1, s2);

        final Integer n1 = 1;
        final Integer n2 = 2;
        assertThrows(AssertionError.class, () -> JAssertions.assertEquals(n1, n2));

        final Integer n3 = 1;
        JAssertions.assertEquals(n1, n3);
    }

}