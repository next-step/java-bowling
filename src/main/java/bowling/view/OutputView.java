package bowling.view;

import bowling.dto.ScoreBoardDto;
import bowling.view.printable.ScoreBoardPrintable;

public class OutputView {
    private OutputView() {}

    public static void printScoreBoard(ScoreBoardDto scoreBoardDto) {
        new ScoreBoardPrintable(scoreBoardDto).print();
    }
}
