package bowling.view;

import java.util.stream.IntStream;

public class ResultView {

    public static final String BAR_DELIMITER = "|";
    public static final String NAME = "NAME";
    public static final int ZERO = 0;
    public static final int FRAME_LENGTH = 11;
    public static final String EMPTY_SPACE = "      ";

    public void printFrames(String name, int round) {
        printSignature();
        printScore(name, round);
    }

    private void printSignature() {
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%5s ",NAME));
        System.out.print(BAR_DELIMITER);
        IntStream.range(ZERO, FRAME_LENGTH)
                .forEach(this::printFrameNumbers);
    }

    private void printFrameNumbers(int number) {
        System.out.print(String.format("  %02d  ", number));
        System.out.print(BAR_DELIMITER);
    }

    private void printScore(String name, int round) {
        System.out.println();
        System.out.print(BAR_DELIMITER);
        System.out.print(String.format("%5s ",name));
        System.out.print(BAR_DELIMITER);
        IntStream.range(round, FRAME_LENGTH)
                .forEach(this::printEmptyScore);
    }

    private void printEmptyScore(int number) {
        System.out.print(EMPTY_SPACE);
        System.out.print(BAR_DELIMITER);
    }

}
