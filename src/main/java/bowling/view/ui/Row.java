package bowling.view.ui;

import bowling.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Row {

    private static final String BORDER = "|";
    private static final String BLANK_STRING = " ";
    private static final String EMPTY_STRING = "";
    private static final int FRAME_SIZE = 6;
    private static final int RIGHT_ALIGN_SIZE = FRAME_SIZE - 1;

    private final List<Cell> cells;

    private Row() {
        this(new ArrayList<>());
    }

    private Row(List<Cell> cells) {
        this.cells = cells;
    }

    public static Row create() {
        return new Row();
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public String row() {
        return cells.stream()
                .map(this::cell)
                .collect(Collectors.joining(BORDER, head(), trail()));
    }

    private String cell(Cell cell) {
        if (cell.getAlign() == Align.RIGHT) {
            return StringUtils.padLeft(cell.data(), RIGHT_ALIGN_SIZE) + BLANK_STRING;
        }
        return StringUtils.alignCenter(cell.data(), FRAME_SIZE);
    }

    private String head() {
        if (!cells.isEmpty()) {
            return BORDER;
        }
        return EMPTY_STRING;
    }

    private String trail() {
        if (!cells.isEmpty()) {
            return BORDER + System.lineSeparator();
        }
        return EMPTY_STRING;
    }
}
