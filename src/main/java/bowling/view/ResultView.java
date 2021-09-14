package bowling.view;

import bowling.domain.*;

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

    public static void printInit(final BowlingGames bowlingGames) {
        System.out.println(TITLE);
        for(BowlingGame bowlingGame : bowlingGames.games()) {
            printPlayerGame(bowlingGame);
        }
    }

    private static void printPlayerGame(BowlingGame bowlingGame) {
        printName(bowlingGame.player());
        IntStream.range(FIRST_NUMBER, LAST_NUMBER)
                .forEach(value -> System.out.print(String.format("%5s %s", BLANK, SEPARATOR)));
        System.out.println();
        System.out.print(SEPARATOR);
        printBlock(LAST_NUMBER);
    }

    public static void printResult(final BowlingGames bowlingGames) {
        System.out.println(TITLE);
        for(BowlingGame bowlingGame : bowlingGames.games()) {
            printName(bowlingGame.player());

            printSymbol(bowlingGame.frames());

            printScore(bowlingGame.frames());
        }

    }

    private static void printSymbol(Frames frames) {
        for (Frame frame : frames.value()) {
            System.out.print(String.format("%5s %s", result(frame.pitches()), SEPARATOR));
        }
        printBlock(LAST_NUMBER - frames.size() - 1);
    }

    private static void printScore(Frames frames) {
        System.out.print(String.format("%s%s %s", SEPARATOR, BLANK, SEPARATOR));
        int total = 0;
        for (Frame frame : frames.value()) {
            Score score = frame.score();
            String resultScore = BLANK;
            if (score.canCalculateScore() && frame.isEnd()) {
                total += score.getScore();
                resultScore = String.valueOf(total);
            }
            System.out.print(String.format("%5s %s", resultScore, SEPARATOR));
        }

        printBlock(LAST_NUMBER - frames.size() - 1);

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
