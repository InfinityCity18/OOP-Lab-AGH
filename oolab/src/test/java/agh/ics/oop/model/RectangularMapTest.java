package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {

    @Test
    void placeTest() {
        RectangularMap map = new RectangularMap(6,6);;
        Vector2d pos = new Vector2d(2,3);
        map.place(new Animal(pos));
        assertTrue(map.isOccupied(pos));
    }

    @Test
    void moveTest() {
        RectangularMap map = new RectangularMap(6,6);;
        Vector2d pos = new Vector2d(2,3);
        Vector2d pos2 = new Vector2d(2,4);
        Animal animal = new Animal(pos);
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertTrue(map.isOccupied(pos2));
    }

    @Test
    void objectAtTest() {
        RectangularMap map = new RectangularMap(6,6);;
        assertNull(map.objectAt(new Vector2d(2,3)));
    }

    @Test
    void canMoveToTest() {
        RectangularMap map = new RectangularMap(6,6);;
        Vector2d pos = new Vector2d(2,3);
        Animal animal = new Animal(pos);
        map.place(animal);
        assertFalse(map.canMoveTo(pos));
    }
}
