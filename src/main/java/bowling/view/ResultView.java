package bowling.view;

import bowling.domain.Frames;

import java.util.Map;

public class ResultView {

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
        return "| NAME |";
    }

    private static String headValue() {
        return "|  " + Template.getTEMPLATE().get(0) + " |";
    }

    private static String printTitle(Integer key) {
        if (key < 10 && key > 0) {
            return "  0" + key + "  |";
        }
        return "  " + key + "  |";
    }

    private static String printValue(Frames value) {
        if (value == null) {
            return "  " + "  " + "  |";
        }
        return "  " + value + "  |";
    }
}
