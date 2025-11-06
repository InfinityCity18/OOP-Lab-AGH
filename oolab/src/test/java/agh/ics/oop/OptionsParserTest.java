package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void parse() {
        String[] args = {"f", "b", "l", "l"};
        MoveDirection[] moves = OptionsParser.parseMoves(args);
        assertArrayEquals(new MoveDirection[]{
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.LEFT
        }, moves);
    }
}
