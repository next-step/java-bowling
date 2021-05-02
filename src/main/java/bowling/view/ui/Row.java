package bowling.view.ui;

import bowling.utils.StringUtils;

import java.util.List;

public final class Row {

    private static final String BORDER = "|";
    private static final String BLANK_STRING = " ";
    private static final int FRAME_SIZE = 6;
    private static final int RIGHT_ALIGN_SIZE = FRAME_SIZE - 1;

    private final List<Cell> cells;

    public Row(List<Cell> cells) {
        this.cells = cells;
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public String row() {
        final StringBuilder rowBuilder = new StringBuilder();

        for (Cell cell : cells) {
            buildRow(rowBuilder, cell);
        }
        buildTrail(rowBuilder);

        return rowBuilder.toString();
    }

    private void buildRow(StringBuilder rowBuilder, Cell cell) {
        if (cell.getAlign() == Align.RIGHT) {
            rowBuilder.append(BORDER).append(StringUtils.padLeft(cell.data(), RIGHT_ALIGN_SIZE)).append(BLANK_STRING);
        }
        if (cell.getAlign() == Align.CENTER) {
            rowBuilder.append(BORDER).append(StringUtils.alignCenter(cell.data(), FRAME_SIZE));
        }
    }

    private void buildTrail(StringBuilder rowBuilder) {
        rowBuilder.append(BORDER).append(System.lineSeparator());
    }
}
