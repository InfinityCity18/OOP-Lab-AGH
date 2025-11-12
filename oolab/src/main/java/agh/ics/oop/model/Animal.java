package agh.ics.oop.model;

public class Animal implements WorldElement {

    private MapDirection world_dir;
    private Vector2d pos;

    public Animal() {
        this.pos = new Vector2d(2, 2);
        this.world_dir = MapDirection.NORTH;
    }

    public Animal(Vector2d pos) {
        this.pos = pos;
        this.world_dir = MapDirection.NORTH;
    }

    public Vector2d getPosition() {
        return this.pos;
    }

    public MapDirection getDir() {
        return this.world_dir;
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        switch (direction) {
            case MoveDirection.LEFT -> this.world_dir = this.world_dir.previous();
            case MoveDirection.RIGHT -> this.world_dir = this.world_dir.next();
            case MoveDirection.FORWARD -> {
                Vector2d newPosition = this.pos.add(this.world_dir.toUnitVector());
                if (validator.canMoveTo(newPosition)) this.pos = newPosition;
            }
            case MoveDirection.BACKWARD -> {
                Vector2d newPosition = this.pos.subtract(this.world_dir.toUnitVector());
                if (validator.canMoveTo(newPosition)) this.pos = newPosition;
            }
        }
    }

    @Override
    public String toString() {
        return this.world_dir.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return this.pos.equals(animal.pos) && this.world_dir == animal.world_dir;
    }

    public boolean isAt(Vector2d position) {
        return this.pos.equals(position);
    }
}
