package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Header;
import bowling.domain.Score;

import java.util.List;

public class ResultView {

    private static final String NAME_TITLE = "| NAME |";
    private static final String RIGHT_BOUNDARY = "  |";
    private static final String LEFT_BOUNDARY = "| ";
    private static final String PRETTY_EMPTY = "  ";
    private static final String CORRECTION_ZERO = "0";

    public static void printFrames(List<Frame> frames) {
        System.out.print(headTitle());

        for (int i = 1; i < 11; i++) {
            System.out.print(printTitle(i));
        }
        System.out.println();

        System.out.print(headValue());


        for (Frame frame : frames) {
            int last = frames.get(frames.size() - 1).getPosition();
            if (last == frame.getPosition() && frames.size() != 1) {
                System.out.print(printFrameBySamePosition(frame.getPoint(), frames.get(frames.size() - 1).getPoint()));
            } else {
                System.out.print(printFrame(frame.getPosition(), frame.getPoint()));
            }
        }

        for (int i = 0; i < 10 - frames.size(); i++) {
            System.out.print(printTitle());
        }
        System.out.println();
    }

    private static String printFrame(int position, int point) {
        return PRETTY_EMPTY + point + RIGHT_BOUNDARY;
    }

    private static String printFrameBySamePosition(int first, int second) {
        return PRETTY_EMPTY + first + "|" + second +RIGHT_BOUNDARY;
    }

    private static String printTitle() {
        return PRETTY_EMPTY + PRETTY_EMPTY + RIGHT_BOUNDARY;
    }

    private static String headTitle() {
        return NAME_TITLE;
    }

    private static String headValue() {
        return LEFT_BOUNDARY + Header.getTEMPLATE().get("NAME") + RIGHT_BOUNDARY;
    }

    private static String printTitle(Integer key) {
        if (key < 10 && key > 0) {
            return PRETTY_EMPTY + CORRECTION_ZERO + key + RIGHT_BOUNDARY;
        }
        return PRETTY_EMPTY + key + RIGHT_BOUNDARY;
    }
}
