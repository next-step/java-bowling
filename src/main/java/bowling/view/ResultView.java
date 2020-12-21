package bowling.view;

import java.util.stream.IntStream;

public class ResultView {

    public static final String BAR_DELIMITER = "|";
    public static final String NAME = "NAME";
    public static final int ONE = 1;
    public static final int FRAME_LENGTH = 11;
    public static final String EMPTY_SPACE = "      ";

    public static void printEmptyRecords(String name) {
        printSignature();
        printScore(name, 1);
        System.out.println();
    }

    public static void printFrames(String name, int round) {
        printSignature();
        printScore(name, round);
        System.out.println();
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

    private static void printScore(String name, int round) {
        System.out.println();
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%5s ",name));
        System.out.print(BAR_DELIMITER);

        IntStream.range(ONE, FRAME_LENGTH)
                .forEach(ResultView::printEmptyScore);
    }

    private static void printEmptyScore(int number) {
        System.out.print(EMPTY_SPACE);
        System.out.print(BAR_DELIMITER);
    }

//    private void printPitchScore(int number) {
//        System.out.print(String.format("%3s ", number));
//    }

}
