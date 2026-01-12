package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.AbstractMap;
import java.util.List;
import java.util.Vector;

public class SimulationPresenter implements MapChangeListener {

    @FXML
    private Label infoLabel;
    @FXML
    private Label moveInfoLabel;
    @FXML
    private TextField movesTextField;
    @FXML
    private Canvas mapGrid;

    private static final int CELL_WIDTH = 80;
    private static final int BORDER_OFFSET = 80;

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
        Boundary bounds = map.getCurrentBounds();
        this.mapGrid.setHeight(CELL_WIDTH * Math.abs(bounds.top_right().getY() - bounds.bot_left().getY()));
        this.mapGrid.setWidth(CELL_WIDTH * Math.abs(bounds.top_right().getX() - bounds.bot_left().getX()));

        clearGrid();

        GraphicsContext graphics = mapGrid.getGraphicsContext2D();
        graphics.setStroke(Color.BLACK);
        graphics.setLineWidth(2);
        for (int x = 0; x < mapGrid.getWidth() + 1; x += CELL_WIDTH) {
            graphics.strokeLine(x + BORDER_OFFSET, 0, x + BORDER_OFFSET, mapGrid.getHeight());  // BORDER_OFFSET = BORDER_WIDTH / 2
        }
        for (int y = 0; y < mapGrid.getHeight() + 1; y += CELL_WIDTH) {
            graphics.strokeLine(0, y + BORDER_OFFSET, mapGrid.getWidth(), y + BORDER_OFFSET);  // BORDER_OFFSET = BORDER_WIDTH / 2
        }
        configureFont(graphics, 80, Color.BLACK);
        for (WorldElement element : map.getElements()) {
            Vector2d pos = element.getPosition();
            graphics.fillText(element.toString(), (double) CELL_WIDTH / 2 + CELL_WIDTH * pos.getX(), mapGrid.getHeight() - ((double) CELL_WIDTH / 2 + CELL_WIDTH * pos.getY()));
        }

    }

    private void clearGrid() {
        GraphicsContext graphics = mapGrid.getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, mapGrid.getWidth(), mapGrid.getHeight());
    }

    private void configureFont(GraphicsContext graphics, int size, Color black) {
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.setFont(new Font("Arial", size));
        graphics.setFill(black);
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
            moveInfoLabel.setText(message);
        });
    }
}
