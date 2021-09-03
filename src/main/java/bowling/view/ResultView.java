package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Pins;

public class ResultView {
    private static final String BOUND_LINE = "|";
    private static final String NAME = " NAME ";
    private static final String ZERO = "0";
    private static final int PLAYER_NAME_COLUMN = 0;
    private static final int FINAL_FRAME = 10;
    private static final String BLANK = " ";

    public static void printFrames(String player, Frames frames) {
        for (int numberOfGame = 0; numberOfGame <= FINAL_FRAME; numberOfGame++) {
            printTitle(numberOfGame);
        }

        printPlayer(player);

        for (int numberOfGame = 0; numberOfGame < frames.size(); numberOfGame++) {
            printScore(numberOfGame, frames);
        }
        int lastFrameNumber = FINAL_FRAME - frames.size();

        for (int numberOfGame = 0; numberOfGame < FINAL_FRAME - frames.size(); numberOfGame++) {
            printRemainBoard(numberOfGame, lastFrameNumber);
        }

        System.out.println();
    }

    private static void printRemainBoard(int numberOfGame, int lastFrameNumber) {
        if (numberOfGame == lastFrameNumber - 1) {
            System.out.print(BLANK + BLANK + BLANK + BLANK + BLANK + BLANK + BOUND_LINE);
            return;
        }
        System.out.print(BLANK + BLANK + BLANK + BLANK + BLANK + BOUND_LINE);

    }

    private static void printPlayer(String player) {
        System.out.println();

        System.out.print(BOUND_LINE + BLANK + player + BLANK + BLANK + BOUND_LINE);
    }

    private static void printScore(int numberOfGame, Frames frames) {
        if (frames.get(numberOfGame).isSecondFitchDone()) {
            drawSecondFitch(numberOfGame, frames);
            return;
        }
        if (frames.get(numberOfGame).isFirstFitchDone()) {
            drawFirstFitch(numberOfGame, frames);
        }


    }

    private static void drawFirstFitch(int numberOfGame, Frames frames) {
        if (frames.get(numberOfGame).getFirstPin().equals(Pins.of(10))) {
            System.out.print(BLANK + BLANK + "X " + BLANK + BOUND_LINE);
            return;
        }
        if (frames.get(numberOfGame).getFirstPin().equals(Pins.of(0))) {
            System.out.print(BLANK + BLANK + "-" + BLANK + BOUND_LINE);
            return;
        }

        System.out.print(BLANK + frames.get(numberOfGame).getFirstPin().getFallenPins() + "|" + BLANK + BLANK + BOUND_LINE);
    }

    private static void drawSecondFitch(int numberOfGame, Frames frames) {
//        if (frames.get(numberOfGame).getSecondPin().equals(Pins.of(10))) {
//            System.out.print("X ");
//            return;
//        }
        if (frames.get(numberOfGame).getSecondPin().equals(Pins.of(0))) {
            System.out.print("-");
        }
        System.out.print(BLANK + frames.get(numberOfGame).getFirstPin().getFallenPins() + "|" + frames.get(numberOfGame).getSecondPin().getFallenPins() + BLANK + BOUND_LINE);
    }

    private static void printTitle(int numberOfGame) {
        if (numberOfGame == PLAYER_NAME_COLUMN) {
            System.out.print(BOUND_LINE + NAME + BOUND_LINE);
        }
        if (numberOfGame > PLAYER_NAME_COLUMN && numberOfGame < FINAL_FRAME) {
            System.out.print(BLANK + BLANK + ZERO + numberOfGame + BLANK + BOUND_LINE);
        }
        if (numberOfGame == FINAL_FRAME) {
            System.out.print(BLANK + BLANK + numberOfGame + BLANK + BLANK + BOUND_LINE);
        }
    }
}

