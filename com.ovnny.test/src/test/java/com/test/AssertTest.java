package com.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertTest {

    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "teste".getBytes();
        byte[] atual = "teste".getBytes();
        assertArrayEquals(expected, atual);
    }

    @Test
    public void testAssertEquals() {
        assertEquals("text", "text");
    }

    @Test
    public void testAssertFalse() {
        assertFalse(false);
    }

    @Test
    public void testAssertNotNull() {
        assertNotNull(new Object());
    }

    @Test
    public void testAssertNotSame() {
        assertNotSame(new Object(), new Object());
    }

    @Test
    public void testAssertNull() {
        assertNull(null);
    }

    @Test
    public void testAssertSame() {
        Integer aNumber = Integer.parseInt("768");
        assertSame(aNumber, aNumber);
    }
}
