package bowling.view;

import bowling.ViewUtils;

public class OutputView {
    private static final String BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private void showBoardHeader() {
        ViewUtils.printLine(BOARD_HEADER);
    }
}
