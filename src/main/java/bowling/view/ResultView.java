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
                .forEach(frame -> System.out.print("\t" + frame + "\t|"));
        System.out.println();
    }

    public static void printScore(String name, List<Frame> records) {
        System.out.print("|  " + name + " |");
        records.stream()
                .forEach(frame -> System.out.print("\t" + toFrameScore(frame) + "\t|"));
        System.out.println();
    }

    private static String toFrameScore(Frame frame) {
        return frame.frameState().symbol();
    }
}
