package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine {
    ArrayList<Simulation> simulations;
    ArrayList<Thread> threads;

    public SimulationEngine(ArrayList<Simulation> simulations) {
        this.simulations = simulations;
        this.threads = new ArrayList<>();
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            threads.add(thread);
            thread.start();
        }
    }

    public void awaitSimulationsEnd() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
