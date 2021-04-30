package bowling.view;

import bowling.domain.Player;

public final class ResultView {

    private static final String SCORE_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_BOARD_BODY = "|  %s |      |      |      |      |      |      |      |      |      |      |";

    private static final class ResultViewHolder {
        private static final ResultView instance = new ResultView();
    }

    private ResultView() {
    }

    public static final ResultView getInstance() {
        return ResultViewHolder.instance;
    }

    public final void printScoreBoard(Player player) {
        System.out.println(SCORE_BOARD_HEADER);
    }


}

