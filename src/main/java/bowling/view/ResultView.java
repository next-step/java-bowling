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
            printForFitchCondition(numberOfGame, frames);
        }
    }

    private static void printForFitchCondition(int numberOfGame, Frames frames) {
        if (frames.get(numberOfGame).isFirstFitchDone()) {
            drawFirstFitch(numberOfGame, frames);
        }
        if (frames.get(numberOfGame).isSecondFitchDone()) {
            drawSecondFitch(numberOfGame, frames);
        }
        if (frames.get(numberOfGame).isBonusFitchDone()) {
            drawLastFitch(frames.getLast());
        }
    }

    private static void drawFirstFitch(int numberOfGame, Frames frames) {
        Frame current = frames.get(numberOfGame);
        States states = current.getStates();
        Pins firstPin = current.getFirstPin();

        if (drawIfFinalFrame(numberOfGame, states.getFirstFitch())) {
            return;
        }
        if (states.getFirstFitch() == State.STRIKE) {
            System.out.print(STRIKE);
            return;
        }
        if (states.getFirstFitch() == State.GUTTER) {
            System.out.print(NO_SPACE_GUTTER);
            return;
        }

        System.out.print(BLANK + firstPin.getFallenPins() + BOUND_LINE);
    }

    private static void drawSecondFitch(int numberOfGame, Frames frames) {
        Frame current = frames.get(numberOfGame);
        States states = current.getStates();
        Pins secondPin = current.getSecondPin();

        if (drawIfFinalFrame(numberOfGame, states.getSecondFitch())) {
            return;
        }
        if (states.getSecondFitch() == State.GUTTER) {
            System.out.print(GUTTER);
            return;
        }
        if (states.getSecondFitch() == State.MISS) {
            System.out.print(GUTTER);
            return;
        }
        if (states.getSecondFitch() == State.SPARE) {
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

    private static void drawLastFitch(Frame last) {
        States states = last.getStates();
        Pins bonusPin = last.getBonusPin();

        if (states.getBonusFitch() == State.STRIKE) {
            System.out.print(NO_SPACE_STRIKE);
            return;
        }
        if (states.getBonusFitch() == State.GUTTER) {
            System.out.print(GUTTER);
            return;
        }
        System.out.print(bonusPin.getFallenPins() + BOUND_LINE);
    }

    private static void printRemainBoard(Frames frames) {
        Frame currentFrame = frames.getLast();

        if (currentFrame.isFirstFitchDone() && !currentFrame.isSecondFitchDone()) {
            drawRemainFirstFitch(frames);
            System.out.println();
            return;
        }
        if (currentFrame.isSecondFitchDone()) {
            drawRemainSecondFitch(frames);
            System.out.println();
            return;
        }

    }

    private static void drawRemainSecondFitch(Frames frames) {
        for (int numberOfGame = frames.size(); numberOfGame <= FINAL_FRAME; numberOfGame++) {
            System.out.print(BLANK_FRAME);
        }
    }

    private static void drawRemainFirstFitch(Frames frames) {
        Frame currentFrame = frames.getLast();
        States states = currentFrame.getStates();

        if (states.getFirstFitch() != State.STRIKE) {
            System.out.print(HALF_BLANK_FRAME);
        }

        for (int numberOfGame = frames.size(); numberOfGame <= FINAL_FRAME; numberOfGame++) {
            System.out.print(BLANK_FRAME);
        }
    }


}

