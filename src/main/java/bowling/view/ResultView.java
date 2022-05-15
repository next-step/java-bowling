package bowling.view;

import bowling.domain.Frame;

import java.util.Arrays;
import java.util.List;

public class ResultView {
    private static final List<String> FRAMES = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");

    private ResultView() {
    }

    public static void printLabel() {
        System.out.print("| NAME |");
        FRAMES.stream()
                .forEach(frame -> System.out.print(" " + frame + " |"));
        System.out.println();
    }

    public static void printScore(String name, List<Frame> records) {
        System.out.print("|  " + name + " |");
        if (records.size() == 0) {
            printScoreDefault();
            return;
        }
//        records.stream()
//                .filter(frame -> frame.getFirst() != -1)
//                .forEach(frame -> System.out.print(" " + frame.getFirst() + "│")
//                .forEach(frame -> System.out.print(" " + frame.getFirst() + "│" + frame.getSecond() + " |"));
    }

    private static void printScoreDefault() {
        FRAMES.stream()
                .forEach(frame -> System.out.print("    |"));
        System.out.println();
    }
}
