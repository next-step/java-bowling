package bowling.view;

import bowling.domain.*;

public class ResultView {

    private static final String NAME_TITLE = "| NAME |";
    private static final String RIGHT_BOUNDARY = "  |";
    private static final String LEFT_BOUNDARY = "|  ";
    private static final String PRETTY_EMPTY = "  ";
    private static final String VALUE_EMPTY = " ";
    private static final String VALUE_BAR = "|";
    private static final String CORRECTION_ZERO = "0";

    public static void printFrames(Frames frames) {
        System.out.print(headTitle());

        for (int i = 1; i < 11; i++) {
            System.out.print(printTitle(i));
        }
        System.out.println();
        System.out.print(headValue());


        for (Frame frame : frames.getFrames()) {
            if (frame.isNext()) {
                System.out.print(LEFT_BOUNDARY);
//                System.out.print(ScoreBoard.getValue(frame.getPosition()).getFirst());
                System.out.print(ScoreBoard.convertScore(frame.getPosition(), frame.getCount()));
            } else {
                System.out.print(VALUE_BAR);
                System.out.print(ScoreBoard.convertScore(frame.getPosition(), frame.getCount()));
//                System.out.print(ScoreBoard.getValue(frame.getPosition()).getSecond());
            }
        }

        for (int i = 0; i < 10 - frames.getLastFramePosition() ; i++) {
            if (i == 0) {
                System.out.print(PRETTY_EMPTY);
            }
            System.out.print(printTitle());
        }
        System.out.println(VALUE_BAR);
        System.out.println();
    }

    private static String printTitle() {
        return LEFT_BOUNDARY + PRETTY_EMPTY + PRETTY_EMPTY;
    }

    private static String headTitle() {
        return NAME_TITLE;
    }

    private static String headValue() {
        return LEFT_BOUNDARY + ScoreBoard.getTEMPLATE().get("NAME") + VALUE_EMPTY;
    }

    private static String printTitle(Integer key) {
        if (key < 10 && key > 0) {
            return PRETTY_EMPTY + CORRECTION_ZERO + key + RIGHT_BOUNDARY;
        }
        return PRETTY_EMPTY + key + RIGHT_BOUNDARY;
    }
}
