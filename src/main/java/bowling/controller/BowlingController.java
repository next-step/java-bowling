package bowling.controller;

import bowling.domain.Bowling;
import bowling.view.InputView;
//import bowling.view.OutputView;

public class BowlingController {

    private InputView inputView = new InputView();
//    private OutputView outputView = new OutputView();

    public void play() {
        Bowling bowling = new Bowling(inputView.getName());
//        outputView.printGame(bowlingGame);
        while (!bowling.isEnd()) {
            bowling.play(inputView.getPoint(bowling.frameCount()));
//            outputView.printGame(bowlingGame);
        }
    }
}
