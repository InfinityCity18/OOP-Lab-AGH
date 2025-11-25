package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    public void moveTest() {
        List<MoveDirection> directions = OptionsParser.parseMoves(
            new String[] {"f", "f", "f", "l", "f", "l", "b", "f", "b", "f", "f"}
        );
        List<Vector2d> positions = List.of(new Vector2d(1, 1));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        Animal animal_exp = new Animal(new Vector2d(0,2));
        animal_exp.move(MoveDirection.LEFT);
        animal_exp.move(MoveDirection.LEFT);
        assertEquals(animal_exp, simulation.getAnimal(0));
    }

    @Test
    public void move2Test() {
        List<MoveDirection> directions = OptionsParser.parseMoves(
                new String[] {"f","f", "f", "f", "f", "f", "l", "r", "f", "f", "l", "r", "b", "b", "f", "f", "b", "b", "f", "f", "f", "f"}
        );
        List<Vector2d> positions = List.of(new Vector2d(1, 1), new Vector2d(2, 1));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
        Animal animal_exp1 = new Animal(new Vector2d(0,2));
        Animal animal_exp2 = new Animal(new Vector2d(3,2));
        animal_exp1.move(MoveDirection.LEFT);
        animal_exp1.move(MoveDirection.LEFT);
        animal_exp2.move(MoveDirection.RIGHT);
        animal_exp2.move(MoveDirection.RIGHT);
        assertEquals(animal_exp1, simulation.getAnimal(0));
        assertEquals(animal_exp2, simulation.getAnimal(1));
    }
}
