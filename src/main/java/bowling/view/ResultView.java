package bowling.view;

import bowling.domain.Game;

public class ResultView {

    private static final String BOARD = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    public void printScoreBoard(Game game) {
        System.out.println(BOARD);
    }
}
