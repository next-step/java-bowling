package bowling.view;

import bowling.domain.*;

public class ResultView {
    private static final String BOUND_LINE = "|";
    private static final int FINAL_FRAME = 9;
    private static final String BLANK = " ";
    private static final String HALF_BLANK_FRAME = "  |";
    private static final String BLANK_FRAME = "     |";
    private static final String TITLE = "| NAME |  01 |  02 |  03 |  04 |  05 |  06 |  07 |  08 |  09 |  10 |";
    private static final String STRIKE = "  X  |";
    private static final String NO_SPACE_STRIKE = "X|";
    private static final String NO_SPACE_SPARE = "/|";
    private static final String NO_SPACE_GUTTER = " -|";
    private static final String GUTTER = "- |";
    private static final String SPARE = "/ |";

    public static void printFrames(String player, Frames frames) {
        printTitle();

        printPlayer(player);

        printScore(frames);

        printRemainBoard(frames);
    }

    private static void printTitle() {
        System.out.println(TITLE);
    }

    private static void printPlayer(String player) {
        System.out.print(BOUND_LINE + BLANK + player + BLANK + BLANK + BOUND_LINE);
    }

    private static void printScore(Frames frames) {
        for (int numberOfGame = 0; numberOfGame < frames.size(); numberOfGame++) {
            printForPitchCondition(numberOfGame, frames);
        }
    }

    private static void printForPitchCondition(int numberOfGame, Frames frames) {
        Frame currentFrame = frames.get(numberOfGame);

        if (currentFrame.isFirstPitchDone()) {
            drawFirstPitch(numberOfGame, currentFrame);
        }
        if (currentFrame.isSecondPitchDone()) {
            drawSecondPitch(numberOfGame, currentFrame);
        }
        if (currentFrame.isBonusPitchDone()) {
            drawLastPitch(currentFrame);
        }
    }

    private static void drawFirstPitch(int numberOfGame, Frame currentFrame) {
        States states = currentFrame.getStates();
        Pins firstPin = currentFrame.getFirstPin();

        if (drawIfFinalFrame(numberOfGame, states.getFirstPitch())) {
            return;
        }
        if (states.getFirstPitch() == State.STRIKE) {
            System.out.print(STRIKE);
            return;
        }
        if (states.getFirstPitch() == State.GUTTER) {
            System.out.print(NO_SPACE_GUTTER);
            return;
        }

        System.out.print(BLANK + firstPin.getFallenPins() + BOUND_LINE);
    }

    private static void drawSecondPitch(int numberOfGame, Frame currentFrame) {
        States states = currentFrame.getStates();
        Pins secondPin = currentFrame.getSecondPin();

        if (drawIfFinalFrame(numberOfGame, states.getSecondPitch())) {
            return;
        }
        if (states.getSecondPitch() == State.GUTTER) {
            System.out.print(GUTTER);
            return;
        }
        if (states.getSecondPitch() == State.MISS) {
            System.out.print(GUTTER);
            return;
        }
        if (states.getSecondPitch() == State.SPARE) {
            System.out.print(SPARE);
            return;
        }
        System.out.print(secondPin.getFallenPins() + BLANK + BOUND_LINE);
    }

    private static boolean drawIfFinalFrame(int numberOfGame, State state) {
        if (numberOfGame == FINAL_FRAME && state == State.STRIKE) {
            System.out.print(NO_SPACE_STRIKE);
            return true;
        }
        if (numberOfGame == FINAL_FRAME && state == State.GUTTER) {
            System.out.print(NO_SPACE_GUTTER);
            return true;
        }
        if (numberOfGame == FINAL_FRAME && state == State.MISS) {
            System.out.print(GUTTER);
            return true;
        }
        if (numberOfGame == FINAL_FRAME && state == State.SPARE) {
            System.out.print(NO_SPACE_SPARE);
            return true;
        }
        return false;
    }

    private static void drawLastPitch(Frame last) {
        States states = last.getStates();
        Pins bonusPin = last.getBonusPin();

        if (states.getBonusPitch() == State.STRIKE) {
            System.out.print(NO_SPACE_STRIKE);
            return;
        }
        if (states.getBonusPitch() == State.GUTTER) {
            System.out.print(GUTTER);
            return;
        }
        System.out.print(bonusPin.getFallenPins() + BOUND_LINE);
    }

    private static void printRemainBoard(Frames frames) {
        Frame currentFrame = frames.getLast();

        if (currentFrame.isFirstPitchDone() && !currentFrame.isSecondPitchDone()) {
            drawRemainFirstPitch(frames);
            System.out.println();
            return;
        }
        if (currentFrame.isSecondPitchDone()) {
            drawRemainSecondPitch(frames);
            System.out.println();
            return;
        }
    }

    private static void drawRemainSecondPitch(Frames frames) {
        for (int numberOfGame = frames.size(); numberOfGame <= FINAL_FRAME; numberOfGame++) {
            System.out.print(BLANK_FRAME);
        }
    }

    private static void drawRemainFirstPitch(Frames frames) {
        Frame currentFrame = frames.getLast();
        States states = currentFrame.getStates();

        if (states.getFirstPitch() != State.STRIKE) {
            System.out.print(HALF_BLANK_FRAME);
        }

        for (int numberOfGame = frames.size(); numberOfGame <= FINAL_FRAME; numberOfGame++) {
            System.out.print(BLANK_FRAME);
        }
    }
}

