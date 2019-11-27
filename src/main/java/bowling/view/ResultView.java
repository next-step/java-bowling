package bowling.view;

import bowling.domain.*;

public class ResultView {

    private static final String NAME_TITLE = "| NAME |";
    private static final String RIGHT_BOUNDARY = "  |";
    private static final String LEFT_BOUNDARY = "|  ";
    private static final String PRETTY_EMPTY = "  ";
    private static final String VALUE_BAR = "|";
    private static final String CORRECTION_ZERO = "0";

    public static void printFrames(Frames frames) {
        printRound();
        for (Frame frame : frames.getFrames()) {
            System.out.print(VALUE_BAR);
            if (frame.isRemain()) {
                System.out.print(PRETTY_EMPTY);
            }
            System.out.print(printFrame(frame));
        }
        for (int i = 0; i < 10 - frames.getLastFramePosition() ; i++) {
            if (i == 0) {
                System.out.print(PRETTY_EMPTY+printTitle());
            } else {
                System.out.print(printTitle());
            }
        }
        System.out.println();
    }

    private static String printFrame(Frame frame) {
        if (frame.isCountRemain()) {
            return ScoreBoard.convertScoreByCountRemain(frame.getPosition());
        }
        return ScoreBoard.convertScore(frame.getPosition());
    }

    public static void printInitial() {
        printRound();
        for (int i = 0; i < 10 ; i++) {
            System.out.print(printTitle() + PRETTY_EMPTY + PRETTY_EMPTY + PRETTY_EMPTY);
        }
        System.out.println(VALUE_BAR);
    }

    private static void printRound() {
        System.out.print(headTitle());
        for (int i = 1; i < 11; i++) {
            System.out.print(printTitle(i));
        }
        System.out.println();
        System.out.print(headValue());
    }

    private static String printTitle() {
        return VALUE_BAR;
    }

    private static String headTitle() {
        return NAME_TITLE;
    }

    private static String headValue() {
        return LEFT_BOUNDARY + ScoreBoard.getTEMPLATE().get("NAME") + " ";
    }

    private static String printTitle(Integer key) {
        if (key < 10 && key > 0) {
            return PRETTY_EMPTY + CORRECTION_ZERO + key + RIGHT_BOUNDARY;
        }
        return PRETTY_EMPTY + key + RIGHT_BOUNDARY;
    }
}
