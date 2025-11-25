package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals;
    private List<MoveDirection> moves;

    public Simulation(List<Vector2d> init_positions, List<MoveDirection> moves) {
        this.moves = moves;
        animals = new ArrayList<>();
        for (Vector2d position : init_positions) {
            animals.add(new Animal(position));
        }
    }

    public Animal getAnimal(int index) {
        return this.animals.get(index);
    }

    public void run() {
        int mod = this.animals.size();
        int counter = 0;

        for (MoveDirection move: this.moves) {
            Animal animal = this.animals.get(counter % mod);
            animal.move(move);
            System.out.println("Zwierzak: " + counter % mod + ": " + animal );
            counter++;
        }

    }
}
