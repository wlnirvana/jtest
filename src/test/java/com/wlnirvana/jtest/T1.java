package com.wlnirvana.jtest;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;

class T1 {

    /**
     * Check that {@link JTest} can only be used on methods
     */
    @Test
    public void injectAnnotationTarget() {
        assertEquals(List.of(METHOD), List.of(JTest.class.getAnnotation(Target.class).value()));
    }

}