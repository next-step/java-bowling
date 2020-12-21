package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.frame.Frames;
import bowling.state.BowlingState;

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


    public static void printScoreBoard(Bowling bowling) {
        printBoardHeader();
        printBoardBody(bowling);
    }

    private static void printBoardBody(Bowling bowling) {
        printPlayerName(bowling);
        printPlayerScore(bowling);
        lineSeparator();
        lineSeparator();
    }

    private static void printPlayerScore(Bowling bowling) {
        for (BowlingState state : bowling.getState()) {
            printResult(state);
        }
    }

    private static void printResult(BowlingState state) {
        System.out.print(printScore(state));
    }

    private static String printScore(BowlingState state) {
        return String.format(PLAYER_SCORE, state.printResult() );
    }

    private static void printPlayerName(Bowling bowling) {
        System.out.print(String.format(PLAYER_NAME, bowling.getPlayerName()));
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
