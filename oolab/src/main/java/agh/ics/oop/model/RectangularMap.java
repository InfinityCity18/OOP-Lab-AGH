package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    public final int width;
    public final int height;

    public RectangularMap(int width, int height) {
        Vector2d bot_left = new Vector2d(0,0);
        Vector2d top_right = new Vector2d(width - 1, height - 1);
        super(bot_left, top_right);
        this.width = width;
        this.height = height;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (isOccupied(position)) {
            return this.animal_map.get(position);
        } else {
            return null;
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(bot_left) && position.precedes(top_right) && super.canMoveTo(position);
    }
}