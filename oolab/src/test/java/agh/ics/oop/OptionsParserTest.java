package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.exceptions.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void parse_test1() {
        String[] args = {"f", "b", "l", "l"};
        List<MoveDirection> moves = OptionsParser.parseMoves(args);
        assertEquals(Arrays.asList(new MoveDirection[]{
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.LEFT
        }), moves);
    }

    @Test
    public void parse_test2() {
        String[] args = {};
        List<MoveDirection> moves = OptionsParser.parseMoves(args);
        assertTrue(moves.isEmpty());
    }

    @Test
    public void parse_test3() {
        String[] args = {"f", "wahuabb", "iawhiahwoiahil", "l"};
        try {
            List<MoveDirection> moves = OptionsParser.parseMoves(args);
            fail();
        }  catch (IllegalArgumentException _) {
        }
    }
}
