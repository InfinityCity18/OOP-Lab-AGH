package agh.ics.oop;

import agh.ics.oop.model.*;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        ConsoleMapDisplay observer = new ConsoleMapDisplay();
        List<MoveDirection> directions = OptionsParser.parseMoves(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        GrassField grassMap = new GrassField(10, 1);
        RectangularMap rectMap = new RectangularMap(10, 6, 2);
        rectMap.registerObserver(observer);
        grassMap.registerObserver(observer);
        Simulation simulation_grass = new Simulation(positions, directions, grassMap);
        Simulation simulation_rect = new Simulation(positions, directions, rectMap);
        ArrayList<Simulation> simulations = new ArrayList<>();
        simulations.add(simulation_grass);
        simulations.add(simulation_rect);
        SimulationEngine sim_engine = new SimulationEngine(simulations);
        sim_engine.runAsync();
        sim_engine.awaitSimulationsEnd();
        System.out.println("System zakonczyl dzialanie");
    }
}
