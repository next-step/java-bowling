package bowling.view;

import bowling.domain.Frames;

import java.util.Map;

public class ResultView {

    private static final String NAME_TITLE = "| NAME |";
    private static final String RIGHT_BOUNDARY = "  |";
    private static final String LEFT_BOUNDARY = "| ";
    private static final String PRETTY_EMPTY = "  ";
    private static final String CORRECTION_ZERO = "0";

    public static void printTemplate() {
        System.out.print(headTitle());
        for (Map.Entry<Integer, Object> entry : Template.getTEMPLATE().entrySet()) {
            if (entry.getKey() > 0 && entry.getKey() <= 10) {
                System.out.print(printTitle(entry.getKey()));
            }
        }
        System.out.println();
        System.out.print(headValue());
        for (Map.Entry<Integer, Object> entry : Template.getTEMPLATE().entrySet()) {
            if (entry.getKey() > 0 && entry.getKey() <= 10) {
                System.out.print(printValue((Frames) entry.getValue()));
            }

        }
        System.out.println();
    }

    private static String headTitle() {
        return NAME_TITLE;
    }

    private static String headValue() {
        return LEFT_BOUNDARY + Template.getTEMPLATE().get(0) + RIGHT_BOUNDARY;
    }

    private static String printTitle(Integer key) {
        if (key < 10 && key > 0) {
            return PRETTY_EMPTY+ CORRECTION_ZERO + key + RIGHT_BOUNDARY;
        }
        return PRETTY_EMPTY + key + RIGHT_BOUNDARY;
    }

    private static String printValue(Frames value) {
        if (value == null) {
            return PRETTY_EMPTY + PRETTY_EMPTY + RIGHT_BOUNDARY;
        }
        return PRETTY_EMPTY + value + RIGHT_BOUNDARY;
    }
}
