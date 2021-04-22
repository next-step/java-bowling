package bowling.ui;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import bowling.domain.BowlingTurn;

public class ResultView {
    private static final int LAST_FRAME = 10;
    private static final int FIRST_FRAME = 1;
    private static final String PARTITION = "|";

    public static void printInitBowlingBoard(BowlingTurn bowlingTurn) {
        printTitleFrames();
        System.out.print(PARTITION + formatting(bowlingTurn.player()));
        printEmptyFrames(0);
    }

    public static void printBowlingBoard(BowlingTurn bowlingTurn) {
        printTitleFrames();
        printScoreFrames(bowlingTurn);
        printEmptyFrames(bowlingTurn.frames().size());
    }

    private static void printScoreFrames(BowlingTurn bowlingTurn) {
        System.out.print(PARTITION + formatting(bowlingTurn.player()));
        bowlingTurn.frames().stream()
            .map(frame -> formatting(frame.scoreResult()))
            .forEach(System.out::print);
    }

    private static void printTitleFrames() {
        String name = "| NAME |";
        String frames = IntStream.range(FIRST_FRAME, LAST_FRAME).
            mapToObj(i -> "  0" + i + "  ").collect(Collectors.joining(PARTITION));
        System.out.println(name + frames + "|  10  |");
    }

    private static void printEmptyFrames(int size) {
        Stream.generate(() -> formatting(""))
            .limit(LAST_FRAME - size)
            .forEach(System.out::print);
        System.out.println("\n");
    }

    private static String formatting(String input) {
        return String.format("  %-3s " + PARTITION, input);
    }
}
