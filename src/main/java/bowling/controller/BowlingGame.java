package bowling.controller;

import bowling.domain.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {
    public static void main(String[] args) {
        ResultView resultView = new ResultView(InputView.inputLetters(), new NormalFrame(1));
        resultView.print();
    }
}
