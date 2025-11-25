package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void parse() {
        String[] args = {"f", "b", "l", "l"};
        List<MoveDirection> moves = OptionsParser.parseMoves(args);
        assertEquals(Arrays.asList(new MoveDirection[]{
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.LEFT
        }), moves);
    }
}
