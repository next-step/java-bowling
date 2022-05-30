package bowling.view;

import bowling.domain.Frame;

import java.util.*;
import java.util.stream.IntStream;

public class ResultView {
    private static final List<String> FRAMES = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");

    private ResultView() {
    }

    public static void printBeforeGame(String name) {
        printLabel();
        printSymbol(name, new ArrayList<Frame>());
    }

    public static void printGameInProgress(String name, List<Frame> records) {
        printLabel();
        printSymbol(name, records);
    }

    private static void printLabel() {
        System.out.print("| NAME |");
        FRAMES.stream()
                .forEach(frame -> System.out.print("\t" + frame + "\t|"));
        System.out.println();
    }

    private static void printSymbol(String name, List<Frame> records) {
        System.out.print("|  " + name + " |");
        records.stream()
                .forEach(frame -> System.out.print("\t" + symbol(frame) + "\t|"));
        printBlank(records);
        System.out.println();
    }

    private static String symbol(Frame frame) {
        if (frame.halfOfFrame()) {
            return frame.intermediateState().symbol();
        }
        return frame.intermediateState().symbol() + frame.finalState().symbol();
    }

    private static void printBlank(List<Frame> records) {
        int remains = FRAMES.size() - records.size();
        if (remains != 0) {
            IntStream.range(0, remains)
                    .forEach((remain -> System.out.print("\t\t|")));
        }
    }
}
