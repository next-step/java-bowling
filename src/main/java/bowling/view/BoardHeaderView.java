package bowling.view;

import bowling.utils.StringUtils;
import bowling.view.ui.Cell;
import bowling.view.ui.Row;

public final class BoardHeaderView {

    public static final Row ROW;
    private static final int GAME_SIZE = 10;
    private static final String PLAYER_NAME_HEADER = "NAME";
    private static final int NUMBER_PAD_SIZE = 2;

    static {
        ROW = row();
    }

    private static Row row() {
        final Row boardRow = Row.create();

        boardRow.addCell(Cell.center(PLAYER_NAME_HEADER));

        for (int i = 1; i <= GAME_SIZE; i++) {
            boardRow.addCell(Cell.center(StringUtils.padZero(i, NUMBER_PAD_SIZE)));
        }

        return boardRow;
    }
}
