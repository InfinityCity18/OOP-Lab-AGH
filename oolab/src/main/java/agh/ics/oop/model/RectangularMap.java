package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private Map<Vector2d, Animal> animal_map;
    public final int width;
    public final int height;
    private final Vector2d bot_left;
    private final Vector2d top_right;

    public RectangularMap(int width, int height) {
        this.animal_map = new HashMap<>();
        this.width = width;
        this.height = height;
        this.bot_left = new Vector2d(0,0);
        this.top_right = new Vector2d(this.width - 1, this.height - 1);

    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPos())) {
            this.animal_map.put(animal.getPos(), animal);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animal_map.get(animal.getPos()) == animal) {
            animal_map.remove(animal.getPos(), animal);
            animal.move(direction, this);
            animal_map.put(animal.getPos(), animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animal_map.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        if (isOccupied(position)) {
            return this.animal_map.get(position);
        } else {
            return null;
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(bot_left) && position.precedes(top_right) && !isOccupied(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.bot_left, this.top_right);
    }
}