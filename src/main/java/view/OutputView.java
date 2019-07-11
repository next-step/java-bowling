package view;

import domain.PlayerName;
import domain.ScoreBoard;

public class OutputView {
    private static final String UPPER_SIDE_OF_SCORE_BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public static void printScoreBoard(PlayerName playerName) {
        ScoreBoard scoreBoard = ScoreBoard.of(playerName, scores);
        System.out.println(UPPER_SIDE_OF_SCORE_BOARD);
        System.out.println(scoreBoard.printBoard());
    }
}
