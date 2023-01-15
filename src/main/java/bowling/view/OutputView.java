package bowling.view;

import bowling.domain.ResultLines;

import java.util.List;

public class OutputView {

    public static final String FRAME_DELIMETER = "|";

    public static void printFrameResult(ResultLines results) {
        printResult(results.frameNumbers());
        System.out.println();
        printResult(results.frameResults());
        System.out.println();
        printResult(results.frameScores());

        System.out.println();
        System.out.println();
    }

    private static void printResult(List<String> results) {
        printFrameDelimeter();
        for (String result : results) {
            System.out.print(formatMessage(result));
            printFrameDelimeter();
        }
    }

    private static void printFrameDelimeter() {
        System.out.print(FRAME_DELIMETER);
    }

    private static String formatMessage(String message) {
        return String.format("%6s", String.format("%-4s", message));
    }

}
