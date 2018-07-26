package cisco.java.challenge.impl;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GNodeImplTest {

    @Test
    public void equalsReflexive() {
        GNodeImpl x = new GNodeImpl("A");

        Assert.assertTrue("Object x should be reflexively equal to itself.", x.equals(x));
    }

    @Test
    public void equalsSymmetric() {
        GNodeImpl x = new GNodeImpl("B");
        GNodeImpl y = new GNodeImpl("B");

        assertTrue("x and y should be symmetric equal to each other.", x.equals(y));

        assertTrue("y and x should be symmetric equal to each other.", y.equals(x));
    }

    @Test
    public void equalsConsistent() {
        GNodeImpl x = new GNodeImpl("B");
        GNodeImpl y = new GNodeImpl("C");

        assertEquals("x and y should be consistently return true or consistently return false.", x.equals(y), x.equals(y));
    }

    @Test
    public void equalsTransitive() {
        GNodeImpl x = new GNodeImpl("C");
        GNodeImpl y = new GNodeImpl("C");
        GNodeImpl z = new GNodeImpl("C");

        assertTrue("x should transitively be equal to y.", x.equals(y));

        assertTrue("y should transitively be equal to z.", y.equals(z));

        assertTrue("x should transitively be equal to z.", x.equals(z));
    }

    @Test
    public void equalsNull() {
        GNodeImpl x = new GNodeImpl("B");

        assertFalse("x should not be equals to null", x.equals(null));

    }

    @Test
    public void hashCodeConsistent() {
        GNodeImpl x = new GNodeImpl("B");

        assertEquals("the hashCode method must consistently return the same integer.", x.hashCode(), x.hashCode());
    }

    @Test
    public void hashCodeEquality() {
        GNodeImpl x = new GNodeImpl("B");
        GNodeImpl y = new GNodeImpl("B");

        assertTrue("if x and x are equals, then they should have the same hashcode.", x.equals(y));
        assertEquals("if x and x are equals, then they should have the same hashcode.", x.hashCode(), y.hashCode());
    }


}