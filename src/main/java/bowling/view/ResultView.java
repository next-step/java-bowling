package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Pins;

public class ResultView {
    private static final String BOUND_LINE = "|";
    private static final int FINAL_FRAME = 10;
    private static final String BLANK = " ";
    private static final String BLANK_FRAME = "     |";
    private static final String TITLE = "| NAME |  01 |  02 |  03 |  04 |  05 |  06 |  07 |  08 |  09 |  10 |";

    public static void printFrames(String player, Frames frames) {
        printTitle();

        printPlayer(player);

        printScore(frames);

        printRemainBoard(frames);
    }

    private static void printRemainBoard(Frames frames) {
        for (int numberOfGame = 0; numberOfGame < FINAL_FRAME - frames.size(); numberOfGame++) {
            System.out.print(BLANK_FRAME);
        }
        System.out.println();
    }

    private static void printScore(Frames frames) {
        for (int numberOfGame = 0; numberOfGame < frames.size(); numberOfGame++) {
            printForFitchCondition(numberOfGame, frames);
        }
    }

    private static void printForFitchCondition(int numberOfGame, Frames frames) {
        if (frames.get(numberOfGame).getFrameNumber() == FINAL_FRAME && frames.getLast().isBonusFitchDone()) {
            drawLastFitch(frames.getLast());
            return;
        }
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

        System.out.print(BLANK + frames.get(numberOfGame).getFirstPin().getFallenPins() + BOUND_LINE + BLANK + BLANK + BOUND_LINE);
    }

    private static void drawSecondFitch(int numberOfGame, Frames frames) {
        Frame current = frames.get(numberOfGame);
        Pins firstPin = current.getFirstPin();
        Pins secondPin = current.getSecondPin();

        if (firstPin.equals(Pins.of(0))) {
            System.out.print(BLANK + BLANK + "-" + BOUND_LINE + frames.get(numberOfGame).getSecondPin().getFallenPins() + BOUND_LINE);
            return;
        }
        if (secondPin.equals(Pins.of(0))) {
            System.out.print("-");
        }
        System.out.print(BLANK + frames.get(numberOfGame).getFirstPin().getFallenPins() + BOUND_LINE + frames.get(numberOfGame).getSecondPin().getFallenPins() + BLANK + BOUND_LINE);
    }

    private static void drawLastFitch(Frame last) {
        if (last.getFirstPin().equals(Pins.of(10))) {
            System.out.print(BLANK + BLANK + "X " + BLANK + BOUND_LINE);
            return;
        }
        if (last.getFirstPin().equals(Pins.of(0))) {
            System.out.print(BLANK + BLANK + "-" + BLANK + BOUND_LINE);
            return;
        }
        if (last.getSecondPin().equals(Pins.of(0))) {
            System.out.print("-");
        }
        System.out.print(last.getFirstPin().getFallenPins() + BOUND_LINE + last.getSecondPin().getFallenPins() + BOUND_LINE + last.getBonusPin().getFallenPins() + BOUND_LINE);
    }

    private static void printPlayer(String player) {
        System.out.print(BOUND_LINE + BLANK + player + BLANK + BLANK + BOUND_LINE);
    }

    private static void printTitle() {
        System.out.println(TITLE);
    }
}

