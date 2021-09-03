package bowling.view;

import bowling.domain.Frames;

public class ResultView {
    private static final String BOUND_LINE = "|";
    private static final String NAME = " NAME ";
    private static final String ZERO = "0";
    private static final int PLAYER_NAME_COLUMN = 0;
    private static final int FINAL_FRAME = 10;
    private static final String BLANK = " ";

//    public static void printFrames(String player, Frames frames) {
//        for (int numberOfGame = 0; numberOfGame <= FINAL_FRAME; numberOfGame++) {
//            printTitle(numberOfGame);
//        }
//
//        System.out.println();
//
//        for (int numberOfGame = 0; numberOfGame <= FINAL_FRAME; numberOfGame++) {
//            printScore(player, numberOfGame, frames);
//        }
//
//        System.out.println();
//    }
//
//    private static void printScore(String player, int numberOfGame, Frames frames) {
//        if (numberOfGame == PLAYER_NAME_COLUMN) {
//            System.out.print(BOUND_LINE + BLANK + player + BLANK + BLANK + BOUND_LINE);
//        }
//        if (numberOfGame > PLAYER_NAME_COLUMN && numberOfGame <= FINAL_FRAME) {
//            if(frames.get(numberOfGame).getFirstPin() == 10) {
//                System.out.print(BLANK + "X " + BLANK + BOUND_LINE);
//            }
//            if(frames.get(numberOfGame).getFirstPin() == 0) {
//                System.out.print(BLANK + "- " + BLANK + BOUND_LINE);
//            }
//            System.out.print(BLANK + frames.get(numberOfGame - 1).getFirstPin() + "|" + BLANK + BOUND_LINE);
//        }
//
//    }
//
//    private static void printTitle(int numberOfGame) {
//        if (numberOfGame == PLAYER_NAME_COLUMN) {
//            System.out.print(BOUND_LINE + NAME + BOUND_LINE);
//        }
//        if (numberOfGame > PLAYER_NAME_COLUMN && numberOfGame < FINAL_FRAME) {
//            System.out.print(BLANK + ZERO + numberOfGame + BLANK + BOUND_LINE);
//        }
//        if (numberOfGame == FINAL_FRAME){
//            System.out.print(BLANK + numberOfGame + BLANK + BOUND_LINE);
//        }
//    }
}

