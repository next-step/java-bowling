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
        records.stream()
                .forEach(frame -> System.out.print(" " + toFrameScore(frame) + " |"));
        System.out.println();
    }

    private static String toFrameScore(Frame frame) {
        int first = frame.getFirst();
        int second = frame.getSecond();


        StringBuilder stringBuilder = new StringBuilder();
        if (first != Frame.BEFORE_BOWLING) {
            stringBuilder.append(convertFirstScore(first));
        }
        if (second != Frame.BEFORE_BOWLING) {
            stringBuilder.append(convertSecondScore(first, second));
        }
        return stringBuilder.toString();
    }

    private static String convertFirstScore(int first) {
        if (first == Frame.MAX_NUMBER_OF_PIN) {
            return "X";
        }
        if (first == Frame.ZERO) {
            return  "-";
        }
        return String.valueOf(first);
    }

    private static String convertSecondScore(int first, int second) {
        if (second == Frame.ZERO) {
            return "│-";
        }
        if (second == Frame.MAX_NUMBER_OF_PIN || (first + second) == Frame.MAX_NUMBER_OF_PIN) {
            return "│/";
        }
        return "│" + String.valueOf(second);
    }
}
