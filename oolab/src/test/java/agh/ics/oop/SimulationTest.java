package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.exceptions.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    public void TestBasicOne() {
        List<MoveDirection> directions = OptionsParser.parseMoves(
            new String[] {"f", "f", "f", "l", "f", "l", "b", "f", "b", "f", "f"}
        );
        List<Vector2d> positions = List.of(new Vector2d(1, 1));
        RectangularMap worldMap = new RectangularMap(5,5);
        Simulation simulation = new Simulation(positions, directions, worldMap);
        simulation.run();
        Animal animal_0 = simulation.getAnimal(0);
        assertTrue(animal_0.getDir().equals(MapDirection.SOUTH) && animal_0.getPosition().equals(new Vector2d(0, 2)));
    }

    @Test
    public void TestBasicTwo() {
        List<MoveDirection> directions = OptionsParser.parseMoves(
                new String[] {"f","f", "f", "f", "f", "f", "l", "r", "f", "f", "l", "r", "b", "b", "f", "f", "b", "b", "f", "f", "f", "f"}
        );
        List<Vector2d> positions = List.of(new Vector2d(1, 1), new Vector2d(2, 1));
        RectangularMap worldMap = new RectangularMap(7,4);

        Simulation simulation = new Simulation(positions, directions,  worldMap);
        simulation.run();
        Animal animal_exp1 = simulation.getAnimal(0);
        Animal animal_exp2 = simulation.getAnimal(1);
        assertTrue(animal_exp1.getDir().equals(MapDirection.SOUTH) && animal_exp1.getPosition().equals(new Vector2d(0, 1)));
        assertTrue(animal_exp2.getDir().equals(MapDirection.SOUTH) && animal_exp2.getPosition().equals(new Vector2d(3, 1)));
    }

    @Test
    public void TestBasicTwoButLastBadArg() {
        try {
            List<MoveDirection> directions = OptionsParser.parseMoves(
                    new String[]{"f", "f", "f", "f", "f", "f", "l", "r", "f", "f", "l", "r", "b", "b", "f", "f", "b", "b", "f", "f", "f", "fuwahuiahiuwhauihu"}
            );
            fail(); //spodziewamy sie wyjatku
        } catch (IllegalArgumentException _) {

        }
    }

    @Test
    public void TestNoMoves() {
        List<MoveDirection> directions = OptionsParser.parseMoves(
                new String[] {}
        );
        List<Vector2d> positions = List.of(new Vector2d(1, 1), new Vector2d(2, 1));
        RectangularMap worldMap = new RectangularMap(7,4);

        Simulation simulation = new Simulation(positions, directions,  worldMap);
        simulation.run();
        Animal animal_exp1 = simulation.getAnimal(0);
        Animal animal_exp2 = simulation.getAnimal(1);
        assertTrue(animal_exp1.getDir().equals(MapDirection.NORTH) && animal_exp1.getPosition().equals(new Vector2d(1, 1)));
        assertTrue(animal_exp2.getDir().equals(MapDirection.NORTH) && animal_exp2.getPosition().equals(new Vector2d(2, 1)));
    }

    @Test
    public void TestNoAnimals() {
        try {
            List<MoveDirection> directions = OptionsParser.parseMoves(
                    new String[]{"f", "b", "l", "b"}
            );
            List<Vector2d> positions = List.of();
            RectangularMap worldMap = new RectangularMap(6,4);

            Simulation simulation = new Simulation(positions, directions,  worldMap);
            simulation.run();
            assertTrue(simulation.getAnimals().isEmpty());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void Test0x0map() {
        try {
            List<MoveDirection> directions = OptionsParser.parseMoves(
                    new String[]{"f", "b", "l", "w", "b"}
            );
            List<Vector2d> positions = List.of(new Vector2d(0, 0));
            RectangularMap worldMap = new RectangularMap(0,0);

            Simulation simulation = new Simulation(positions, directions,  worldMap);
            simulation.run();
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void TestPlaceMultipleAnimalsInOneSquare() {
        List<MoveDirection> directions = OptionsParser.parseMoves(
                new String[] {"f"}
        );
        List<Vector2d> positions = List.of(new Vector2d(1, 1), new Vector2d(1, 1));
        RectangularMap worldMap = new RectangularMap(7,4);

        Simulation simulation = new Simulation(positions, directions,  worldMap);
        simulation.run();
        Animal animal_0 = simulation.getAnimal(0);
        assertTrue(simulation.getAnimals().size() == 1);
        assertTrue(animal_0.getDir().equals(MapDirection.NORTH) && animal_0.getPosition().equals(new Vector2d(1, 2)));
    }
}
