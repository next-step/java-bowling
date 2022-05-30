package bowling.view;

import bowling.domain.Frame;
import bowling.domain.LastFrame;
import bowling.domain.NormalFrame;

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
        if (frame instanceof LastFrame) {
            return symbolIfLastFrame((LastFrame) frame);
        }
        return symbolIfNormalFrame((NormalFrame) frame);
    }

    private static String symbolIfNormalFrame(NormalFrame frame) {
        if (frame.firstOfFrame()) {
            return frame.firstState().symbol();
        }
        return frame.firstState().symbol() + frame.secondState().symbol();
    }

    private static String symbolIfLastFrame(LastFrame frame) {
        if (frame.firstOfFrame()) {
            return frame.firstState().symbol();
        }
        if (frame.endFrame()) {
            return frame.firstState().symbol() + frame.secondState().symbol() + ((LastFrame) frame).thirdState().symbol();
        }
        return frame.firstState().symbol() + frame.secondState().symbol();
    }

    private static void printBlank(List<Frame> records) {
        int remains = FRAMES.size() - records.size();
        if (remains != 0) {
            IntStream.range(0, remains)
                    .forEach((remain -> System.out.print("\t\t|")));
        }
    }
}
