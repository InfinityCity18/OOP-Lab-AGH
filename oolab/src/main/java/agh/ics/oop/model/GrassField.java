package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField extends AbstractWorldMap {
    private Map<Vector2d, Grass> grass_map;

    public GrassField(int n_fields) {
        super(new Vector2d(0,0), new Vector2d(0,0));
        this.grass_map = new HashMap<>();

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator( (int) Math.sqrt(10 * n_fields), (int) Math.sqrt(10 * n_fields), n_fields);
        for(Vector2d grassPosition : randomPositionGenerator) {
           this.grass_map.put(grassPosition, new Grass(grassPosition));
           updateBounds(grassPosition);
        }
    }

    public void updateBounds(Vector2d pos) {
        bot_left = bot_left.lowerLeft(pos);
        top_right = top_right.upperRight(pos);
    }

    @Override
    public boolean place(Animal animal) {
        if (super.place(animal)) {
            updateBounds(animal.getPosition());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animal_map.get(animal.getPosition()) == animal) {
            super.move(animal, direction);
            updateBounds(animal.getPosition());
        }
    }

    public WorldElement objectAt(Vector2d position) {
        if (this.animal_map.containsKey(position)) {
            return this.animal_map.get(position);
        } else return this.grass_map.getOrDefault(position, null);
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grass_map.values());
        return elements;
    }
}
