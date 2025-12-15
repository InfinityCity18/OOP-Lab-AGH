package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parseMoves(String[] args) throws IllegalArgumentException {
        List<MoveDirection> moves = new ArrayList<MoveDirection>();
        System.out.println(moves);
        for (String arg : args) {
            switch (arg) {
                case "f" -> moves.add(MoveDirection.FORWARD);
                case "b" -> moves.add(MoveDirection.BACKWARD);
                case "l" -> moves.add(MoveDirection.LEFT);
                case "r" -> moves.add(MoveDirection.RIGHT);
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }
        return moves;
    }
}