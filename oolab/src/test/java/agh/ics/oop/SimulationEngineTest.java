package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationEngineTest {
    @Test
    public void eventsCountTest() {
        int n = 10;
        String[] args = {"f", "b", "b", "r", "l", "r", "f", "b", "l", "f"};
        ConsoleMapDisplay observer = new ConsoleMapDisplay();
        List<MoveDirection> directions = OptionsParser.parseMoves(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        ArrayList<Simulation> simulations = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            GrassField grassMap = new GrassField(10, n + i);
            RectangularMap rectMap = new RectangularMap(10, 6, i);
            rectMap.registerObserver(observer);
            grassMap.registerObserver(observer);
            Simulation simulation_grass = new Simulation(positions, directions, grassMap);
            Simulation simulation_rect = new Simulation(positions, directions, rectMap);
            simulations.add(simulation_grass);
            simulations.add(simulation_rect);
        }
        SimulationEngine sim_engine = new SimulationEngine(simulations);
        sim_engine.runAsync();

        // 2n symulacji, bo grass i rect, args.length polecen ruchu zwierzaka, 2 postawienia - tyle musi byc eventow
        assertEquals( 2*n * (args.length + positions.size()), observer.getEventsCount());

    }
}
