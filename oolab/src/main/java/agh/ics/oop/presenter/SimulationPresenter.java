package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {

    @FXML
    private Label infoLabel;
    @FXML
    private Label moveInfoLabel;
    @FXML
    private TextField movesTextField;
    @FXML
    private Canvas canvas;

    public void onSimulationStartClicked() {
        String[] text = movesTextField.getText().split(" ");
        List<MoveDirection> moves = OptionsParser.parseMoves(text);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        GrassField map = new GrassField(10, 0);
        map.registerObserver(this);
        Simulation sim = new Simulation(positions, moves, map);
        this.mapChanged(map, "Simulation started");
        new Thread(sim).start();
    }

    public void drawMap(WorldMap map) {
        infoLabel.setText(map.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
            moveInfoLabel.setText(message);
        });
    }
}
