package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.Pitch;
import bowling.domain.Pitches;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    private static final String SEPARATOR = "|";
    private static final String TITLE = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BLANK = "     ";
    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 11;

    private ResultView() {
    }

    public static void printInit(final BowlingGame bowlingGame) {
        System.out.println(TITLE);
        printName(bowlingGame.player());
        IntStream.range(FIRST_NUMBER, LAST_NUMBER)
                .forEach(value -> System.out.print(String.format("%5s %s", BLANK, SEPARATOR)));
        System.out.println();
        System.out.println();
    }

    public static void printResult(final BowlingGame bowlingGame) {
        System.out.println(TITLE);
        printName(bowlingGame.player());

        for (Frame frame : bowlingGame.frames().value()) {
            System.out.print(String.format("%5s %s", result(frame.pitches()), SEPARATOR));
        }
        printBlock(LAST_NUMBER - bowlingGame.frames().size() - 1);
        System.out.println();
        System.out.println();
    }

    private static void printBlock(int range) {
        String blankBlock = IntStream.range(0, range)
                .mapToObj(index -> String.format("%5s %s", BLANK, SEPARATOR))
                .collect(Collectors.joining());
        System.out.println(blankBlock);
    }

    private static void printName(String playerName) {
        System.out.print(String.format("%s %4s %s", SEPARATOR, playerName, SEPARATOR));
    }

    private static String result(Pitches pitches) {
        return pitches.value()
                .stream()
                .map(Pitch::value)
                .collect(Collectors.joining(Pitch.SEPARATOR));
    }
}
