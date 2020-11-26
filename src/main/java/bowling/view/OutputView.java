package bowling.view;

import bowling.view.printable.ScoreBoardPrintable;

public class OutputView {
    private OutputView() {}

    public static void printScoreBoard() {
        new ScoreBoardPrintable().print();
    }
}
