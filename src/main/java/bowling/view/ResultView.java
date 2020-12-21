package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.frame.Frames;
import bowling.domain.score.ScoreResult;
import bowling.state.BowlingState;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Created By mand2 on 2020-12-21.
 */
public class ResultView {

    public static final String BOARD_NAME = "| NAME |";
    public static final String BOARD_INDEX = "  %02d  |";
    public static final String PLAYER_NAME = "|  %3s |";
    public static final String DELIMITER = "|";
    public static final String PLAYER_SCORE = " %-5s|";


    public static void printScoreBoard(String playerName, List<ScoreResult> scoreResults) {
        printBoardHeader();
        printBoardBody(playerName, scoreResults);
    }

    private static void printBoardBody(String playerName, List<ScoreResult> scoreResults) {
        printPlayerName(playerName);
        printPlayerScore(scoreResults);
        lineSeparator();
        lineSeparator();
    }

    private static void printPlayerScore(List<ScoreResult> scoreResults) {
        for (ScoreResult scoreResult : scoreResults) {
            printResult(scoreResult);
        }
    }

    private static void printResult(ScoreResult scoreResult) {
        System.out.print(printScore(scoreResult));
    }

    private static String printScore(ScoreResult scoreResult) {
        return String.format(PLAYER_SCORE, scoreResult.printScore());
    }

    private static void printPlayerName(String playerName) {
        System.out.print(String.format(PLAYER_NAME, playerName));
    }


    private static void printBoardHeader() {
        printBoardName();
        printBoardIndex();
        lineSeparator();
    }

    private static void printBoardName() {
        System.out.print(BOARD_NAME);
    }

    private static void printBoardIndex() {
        IntStream.rangeClosed(Frames.START_INDEX, Frames.FINAL_FRAME_INDEX)
                .mapToObj(i -> String.format(BOARD_INDEX, i))
                .forEach(System.out::print);
    }

    private static void lineSeparator() {
        System.out.print(System.lineSeparator());
    }

}
