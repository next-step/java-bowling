package step2.view;

import java.util.stream.IntStream;

public class ResultView {

    private static final String BAR_DELIMITER = "|";
    private static final String NAME = "NAME";
    private static final int ONE = 1;
    private static final int FRAME_LENGTH = 11;
    private static final String EMPTY_SPACE = "      ";
    private static final String HALF_EMPTY_SPACE = "   ";

    private static StringBuilder SCORES = new StringBuilder();

    public static void printEmptyRecords(String name) {
        printSignature();
        printEmptyScore(name);
        System.out.println();
    }

    private static void printEmptyScore(String name) {
        printUsername(name);

        IntStream.range(ONE, FRAME_LENGTH)
                .forEach(ResultView::printEmptyScore);
    }

    private static void printUsername(String name) {
        System.out.println();
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%5s ", name));
        System.out.print(BAR_DELIMITER);
    }

    private static void printSignature() {
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%5s ",NAME));
        System.out.print(BAR_DELIMITER);
        IntStream.range(ONE, FRAME_LENGTH)
                .forEach(ResultView::printFrameNumbers);
    }

    private static void printFrameNumbers(int number) {
        System.out.print(String.format("  %02d  ", number));
        System.out.print(BAR_DELIMITER);
    }

    private static void printEmptyScore(int number) {
        System.out.print(EMPTY_SPACE);
        System.out.print(BAR_DELIMITER);
    }
}
