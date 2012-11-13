package com.indix.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathUtilsTest {
    @Test
    public void shouldReturnMinimumNumber() {
        assertEquals(34, MathUtils.min(41, 34, 56, 82).intValue());
    }

    @Test
    public void shouldReturn0(){
        assertEquals(0, MathUtils.min(0,1).intValue());
    }
}
