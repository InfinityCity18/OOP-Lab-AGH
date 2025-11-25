package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parseMoves(String[] args) {
        List<MoveDirection> moves = new ArrayList<MoveDirection>();
        for (String arg : args) {
            switch (arg) {
                case "f" -> moves.add(MoveDirection.FORWARD);
                case "b" -> moves.add(MoveDirection.BACKWARD);
                case "l" -> moves.add(MoveDirection.LEFT);
                case "r" -> moves.add(MoveDirection.RIGHT);
                default -> {
                }
            }
        }
        return moves;
    }
}
//    public static MoveDirection[] parseMoves(String[] args) {
//        int correct_arg_count = args.length; //nie znamy dynamicznych list wiec nie wiem jak inaczej
//        for (String arg : args) {
//            switch (arg) {
//                case "f":
//                case "b":
//                case "r":
//                case "l":
//                    break; //fallthrough
//                default:
//                    correct_arg_count--;
//                    break;
//            }
//        }
//        MoveDirection[] moves = new MoveDirection[correct_arg_count];
//        int i = 0;
//        for (String arg : args) {
//            switch (arg) {
//                case "f" -> moves[i++] = MoveDirection.FORWARD;
//                case "b" -> moves[i++] = MoveDirection.BACKWARD;
//                case "l" -> moves[i++] = MoveDirection.LEFT;
//                case "r" -> moves[i++] = MoveDirection.RIGHT;
//            }
//        }
//        return moves;
//    }
