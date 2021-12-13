package bowling.view;

import bowling.domain.Board;

public class OutputView {
    private static final String BOARD_ROUND_FORMAT = "| NAME |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |  %02d  |";
    private static final String RESULT_FORMAT = "  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|  %-4s|";
    private static final String NAME_FORMAT = "|  %-3s |";

    public void renderBoard(Board board) {
        System.out.println(String.format(BOARD_ROUND_FORMAT, board.allRoundToIntArray()));
        System.out.println(String.format(NAME_FORMAT, board.userName()) + String.format(RESULT_FORMAT, board.gameResultToStringArray()));
    }
}
