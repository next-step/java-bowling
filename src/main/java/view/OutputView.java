package view;

import domain.BowlingGame;

public class OutputView {
    private static final String UPPER_SIDE_OF_SCORE_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static void printBoard(BowlingGame bowlingGame) {
        System.out.println(UPPER_SIDE_OF_SCORE_BOARD);
        System.out.println(); //TODO: BowlingGame 로직 구현하고 이 부분 바꾸기
        printEmptyLine();
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
