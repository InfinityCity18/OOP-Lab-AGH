package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Animal> animals;
    private List<MoveDirection> moves;
    private WorldMap worldMap;

    public Simulation(List<Vector2d> init_positions, List<MoveDirection> moves, WorldMap worldMap) {
        this.moves = moves;
        this.worldMap = worldMap;
        this.animals = new ArrayList<>();
        for (Vector2d position : init_positions) {
            Animal animal = new Animal(position);
            if (this.worldMap.place(animal)) {
                this.animals.add(animal);
            }
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public Animal getAnimal(int index) {
        return this.animals.get(index);
    }

    public void run() {
        System.out.println(this.worldMap.toString());
        int mod = this.animals.size();
        int counter = 0;

        for (MoveDirection move: this.moves) {
            if (animals.isEmpty()) {
                break;
            }
            Animal animal = this.animals.get(counter % mod);
            this.worldMap.move(animal, move);
            System.out.println(this.worldMap.toString());
            counter++;
        }

    }
}
