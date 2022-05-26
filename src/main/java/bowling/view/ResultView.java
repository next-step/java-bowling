package bowling.view;

import bowling.domain.Board;

public class ResultView {

    private static final String RESULT_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private ResultView() {
    }

    public static void printResult(Board board) {
        System.out.println(RESULT_HEADER);
        System.out.println(board);
    }
}
