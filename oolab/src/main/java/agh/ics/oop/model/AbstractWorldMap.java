package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractWorldMap implements WorldMap {
    protected Vector2d bot_left;
    protected Vector2d top_right;
    protected Map<Vector2d, Animal> animal_map;

    AbstractWorldMap(Vector2d botLeft, Vector2d topRight) {
        bot_left = botLeft;
        top_right = topRight;
        animal_map = new HashMap<>();
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.animal_map.put(animal.getPosition(), animal);
            return true;
        } else {
            return false;
        }
    }

    public void move(Animal animal, MoveDirection direction) {
        if (animal_map.get(animal.getPosition()) == animal) {
            animal_map.remove(animal.getPosition(), animal);
            animal.move(direction, this);
            animal_map.put(animal.getPosition(), animal);
        }
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    abstract public WorldElement objectAt(Vector2d position);

    public List<WorldElement> getElements() {
        return new ArrayList<>(this.animal_map.values());
    }

    public boolean isOccupied(Vector2d position) {
        return this.animal_map.containsKey(position);
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.bot_left, this.top_right);
    }
}
