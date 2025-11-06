package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void testEquals() {
        Vector2d a = new Vector2d(1, 3);
        Vector2d b = new Vector2d(1, 3);
        assertEquals(a, b);
        a = new Vector2d(2, -3);
        b = new Vector2d(1, -3);
        assertNotEquals(a, b);
    }

    @Test
    public void testToString() {
        Vector2d a = new Vector2d(1, 3);
        assertEquals("(1,3)", a.toString());
        a = new Vector2d(21, 13);
        assertEquals("(21,13)", a.toString());
    }

    @Test
    public void testPrecedes() {
        Vector2d a = new Vector2d(1, 3);
        Vector2d b = new Vector2d(2, 4);
        assertTrue(a.precedes(b));
    }

    @Test
    public void testFollows() {
        Vector2d a = new Vector2d(3, 8);
        Vector2d b = new Vector2d(2, 4);
        assertTrue(a.follows(b));
    }

    @Test
    public void testUpperRight() {
        Vector2d a = new Vector2d(3, 1);
        Vector2d b = new Vector2d(2, 4);
        assertEquals(new Vector2d(3,4), a.upperRight(b));
    }

    @Test
    public void testLowerLeft() {
        Vector2d a = new Vector2d(3, 1);
        Vector2d b = new Vector2d(2, 4);
        assertEquals(new Vector2d(2,1), a.lowerLeft(b));
    }

    @Test
    public void testAdd() {
        Vector2d a = new Vector2d(3, 1);
        Vector2d b = new Vector2d(2, 4);
        assertEquals(new Vector2d(5,5), a.add(b));
    }

    @Test
    public void testSubtract() {
        Vector2d a = new Vector2d(3, 1);
        Vector2d b = new Vector2d(2, 4);
        assertEquals(new Vector2d(1,-3), a.subtract(b));
    }

    @Test
    public void testOpposite() {
        Vector2d a = new Vector2d(3, 1);
        assertEquals(new Vector2d(-3,-1), a.opposite());
    }
}
