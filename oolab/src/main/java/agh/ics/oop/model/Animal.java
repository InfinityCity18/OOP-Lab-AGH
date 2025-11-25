package agh.ics.oop.model;

public class Animal {

    private static final int MAP_BOUNDARY_1 = 0;
    private static final int MAP_BOUNDARY_2 = 4;

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

    public void move(MoveDirection direction) {
        switch (direction) {
            case MoveDirection.LEFT -> this.world_dir = this.world_dir.previous();
            case MoveDirection.RIGHT -> this.world_dir = this.world_dir.next();
            case MoveDirection.FORWARD -> {
                switch (this.world_dir) {
                    case NORTH -> this.pos.y = Math.clamp(this.pos.y + 1, MAP_BOUNDARY_1, MAP_BOUNDARY_2);
                    case SOUTH -> this.pos.y = Math.clamp(this.pos.y - 1, MAP_BOUNDARY_1, MAP_BOUNDARY_2);
                    case EAST -> this.pos.x = Math.clamp(this.pos.x + 1, MAP_BOUNDARY_1, MAP_BOUNDARY_2);
                    case WEST -> this.pos.x =Math.clamp(this.pos.x - 1, MAP_BOUNDARY_1, MAP_BOUNDARY_2);
                }
            }
            case MoveDirection.BACKWARD -> {
                switch (this.world_dir) {
                    case NORTH -> this.pos.y = Math.clamp(this.pos.y - 1, MAP_BOUNDARY_1, MAP_BOUNDARY_2);
                    case SOUTH -> this.pos.y = Math.clamp(this.pos.y + 1, MAP_BOUNDARY_1, MAP_BOUNDARY_2);
                    case EAST -> this.pos.x = Math.clamp(this.pos.x - 1, MAP_BOUNDARY_1, MAP_BOUNDARY_2);
                    case WEST -> this.pos.x =Math.clamp(this.pos.x + 1, MAP_BOUNDARY_1, MAP_BOUNDARY_2);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Orientacja: " + this.world_dir + ", Położenie: " + this.pos;
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
