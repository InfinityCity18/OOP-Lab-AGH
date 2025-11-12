package agh.ics.oop.model;

public class Grass implements WorldElement {
    private Vector2d pos;
    public Grass(Vector2d pos) {
        this.pos = pos;
    }

    public Vector2d getPosition() {
        return this.pos;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Grass grass = (Grass) o;
        return this.pos.equals(grass.pos);
    }
}
