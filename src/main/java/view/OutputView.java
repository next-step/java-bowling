package view;

import domain.PlayerName;

public class OutputView {
    private static final String UPPER_SIDE_OF_SCORE_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String INITIAL_SCORE_BOARD_FORMAT = "|  %s |      |      |      |      |      |      |      |      |      |      |";

    public static void printInitialBoard(PlayerName playerName) {
        System.out.println(UPPER_SIDE_OF_SCORE_BOARD);
        System.out.println(String.format(INITIAL_SCORE_BOARD_FORMAT, playerName.getName()));
        printEmptyLine();
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
