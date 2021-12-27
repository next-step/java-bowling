package bowling;

import bowling.controller.BowlingGame;
import bowling.domain.factory.FrameFactory;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Client {
    public static void main(String[] args) {

        BowlingGame bowlingGame = new BowlingGame(new InputView(), new OutputView(), new FrameFactory());
        bowlingGame.start();
    }
}
