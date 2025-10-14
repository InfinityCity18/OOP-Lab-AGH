package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import agh.ics.oop.OptionsParser;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        run(OptionsParser.parseMoves(args));
        System.out.println("system zakończył działanie");
    }

//    public static void run() {
//        System.out.println("zwierzak idzie do przodu");
//    }
//    public static void run(String[] moves) {
//        System.out.println("zwierzak idzie do przodu");
//        for (int i = 0; i < moves.length - 1; i++) {
//            System.out.print(moves[i] + ", ");
//        }
//        System.out.println(moves[moves.length - 1]);
//    }
//    public static void run(String[] moves) {
//        System.out.println("Start");
//        for (String move : moves) {
//            switch (move) {
//                case "f" -> System.out.println("zwierzak idzie do przodu");
//                case "b" -> System.out.println("zwierzak idzie do tyłu");
//                case "r" -> System.out.println("zwierzak idzie skręca w prawo");
//                case "l" -> System.out.println("zwierzak idzie skręca w lewo");
//            }
//        }
//        System.out.println("Stop");
//    }
    public static void run(MoveDirection[] moves) {
        System.out.println("Start");
        for (MoveDirection move : moves) {
            switch (move) {
                case FORWARD -> System.out.println("zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("zwierzak idzie skręca w prawo");
                case LEFT -> System.out.println("zwierzak idzie skręca w lewo");
            }
        }
        System.out.println("Stop");
    }
}
