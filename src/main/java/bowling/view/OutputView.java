package bowling.view;

import bowling.view.ui.Row;

import java.util.List;

public final class OutputView {

    public void printScoreBoard(List<Row> rows) {
        rows.forEach(row -> System.out.print(row.row()));
        System.out.println();
    }
}
