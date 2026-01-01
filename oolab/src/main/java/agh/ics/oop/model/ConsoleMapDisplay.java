package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int total_events;

    public ConsoleMapDisplay() {
        this.total_events = 0;
    }

    public int getEventsCount() {
        return total_events;
    }

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Map id: " + worldMap.getId());
        System.out.println(message);
        System.out.println(worldMap.toString());
        System.out.println("Total events: " + ++total_events);
        System.out.println("\n");
    }
}
