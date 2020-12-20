package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.frame.Frames;

import java.util.stream.IntStream;

/**
 * Created By mand2 on 2020-12-21.
 */
public class ResultView {


    public static final String BOARD_NAME = "| NAME |";
    public static final String BOARD_INDEX = "  %02d  |";
    public static final String PLAYER_NAME = "|  %3s |";

    public static void printScoreBoard(Bowling bowling) {
        printBoardHeader();
        lineSeparator();
        printBoardBody(bowling);
        lineSeparator();
    }

    private static void printBoardBody(Bowling bowling) {
        printPlayerName(bowling);
    }

    private static void printPlayerName(Bowling bowling) {
        System.out.print(String.format(PLAYER_NAME, bowling.getPlayerName()));
    }


    private static void printBoardHeader() {
        printBoardName();
        printBoardIndex();
    }

    private static void printBoardName() {
        System.out.print(BOARD_NAME);
    }

    private static void printBoardIndex() {
        IntStream.range(Frames.START_INDEX, Frames.FINAL_FRAME_INDEX)
                .mapToObj(i -> String.format(BOARD_INDEX, i))
                .forEach(System.out::print);
    }

    private static void lineSeparator() {
        System.out.print(System.lineSeparator());
    }

}
