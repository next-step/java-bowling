package bowling.view;

import bowling.domain.game.Game;

public class OutputView {
    private static final String BOARD_HEAD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    public static final String COLUMN = "|";
    public static final String PLAYER_FORMAT = "|  %-4s|";
    public static final String NORMAL_STATE_FORMAT = "  %-4s|";
    public static final String FINAL_STATE_FORMAT = " %-5s|";
    public static final String BLANK = "      |";

    private OutputView() {
    }

    public static void printBoard(Game game) {
        printBoardHead();
        printGame(game);
    }

    private static void printBoardHead() {
        System.out.println(BOARD_HEAD);
    }

    private static void printGame(Game game) {
        System.out.println(game);
        System.out.println();
    }

    public static void printGameIsDone() {
        System.out.println("볼링 게임이 종료되었습니다.");
    }
}
