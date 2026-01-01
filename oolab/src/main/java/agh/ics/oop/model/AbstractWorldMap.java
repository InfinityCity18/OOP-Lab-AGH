package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractWorldMap implements WorldMap {
    protected ArrayList<MapChangeListener> observers = new ArrayList<>();
    protected Boundary bounds;
    protected Map<Vector2d, Animal> animal_map;
    protected int id;

    AbstractWorldMap(Vector2d botLeft, Vector2d topRight, int id) {
        this.id = id;
        bounds = new Boundary(botLeft, topRight);
        animal_map = new HashMap<>();
    }

    public void place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPosition())) {
            this.animal_map.put(animal.getPosition(), animal);
            this.mapChanged("Placed an animal at %s".formatted(animal.getPosition()));
        } else {
            throw new IncorrectPositionException(animal.getPosition());
        }
    }

    public void move(Animal animal, MoveDirection direction) {
        if (animal_map.get(animal.getPosition()) == animal) {
            animal_map.remove(animal.getPosition(), animal);
            animal.move(direction, this);
            animal_map.put(animal.getPosition(), animal);
            this.mapChanged("Moved an animal to %s".formatted(animal.getPosition()));
        }
    }

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    abstract public WorldElement objectAt(Vector2d position);

    public List<WorldElement> getElements() {
        return new ArrayList<>(this.animal_map.values());
    }

    public Boundary getCurrentBounds() {
        return this.bounds;
    }

    public boolean isOccupied(Vector2d position) {
        return this.animal_map.containsKey(position);
    }

    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        Boundary bounds = this.getCurrentBounds();
        return visualizer.draw(bounds.bot_left(), bounds.top_right());
    }

    public void registerObserver(MapChangeListener observer) {
        this.observers.add(observer);
    }

    public void unregisterObserver(MapChangeListener observer) {
        this.observers.remove(observer);
    }

    public int getId() {
        return this.id;
    }

    public void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
}
