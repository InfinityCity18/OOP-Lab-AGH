package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    void placeTest() {
        GrassField map = new GrassField(10,1);
        Vector2d pos = new Vector2d(2,3);
        try {
            map.place(new Animal(pos)); //musi sie udac, miejsce jest wolne
        } catch (IncorrectPositionException e) {
            fail();
        }
        assertTrue(map.isOccupied(pos));
    }

    @Test
    void moveTest() {
        GrassField map = new GrassField(10,1);
        Vector2d pos = new Vector2d(2,3);
        Vector2d pos2 = new Vector2d(2,4);
        Animal animal = new Animal(pos);
        try {
            map.place(animal); //musi sie udac, miejsce jest wolne
        } catch (IncorrectPositionException e) {
            fail();
        }
        map.move(animal, MoveDirection.FORWARD);
        assertTrue(map.isOccupied(pos2));
    }

    @Test
    void objectAtTest() {
        GrassField map = new GrassField(10, 1);
        assertEquals(map.objectAt(new Vector2d(2,3)), null);
    }

    @Test
    void canMoveToTest() {
        RectangularMap map = new RectangularMap(6,6, 1);;
        Vector2d pos = new Vector2d(2,3);
        Animal animal = new Animal(pos);
        try {
            map.place(animal);
        }  catch (IncorrectPositionException e) {
            fail();
        }
        assertFalse(map.canMoveTo(pos));
    }
}
