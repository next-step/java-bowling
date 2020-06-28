package bowling.ui;

import bowling.domain.player.Player;

public class ResultView {
    private static final String ROUND_SCORE_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DEFAULT_SCORE_BOARD = "|  %s |      |      |      |      |      |      |      |      |      |      |";

    public static void printDefaultScoreBoard(Player player) {
        System.out.println(ROUND_SCORE_BOARD);
        System.out.println(String.format(DEFAULT_SCORE_BOARD, player.toString()));
    }
}
