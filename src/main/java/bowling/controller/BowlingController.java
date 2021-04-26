package bowling.controller;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public void run() {
        Player player = Player.from(InputView.getPlayerName());
        Frames frames = Frames.init();
        OutputView.showScoreBoard(frames, player);
        while (frames.isContinue()) {
            int topplePin = InputView.getTopplePin(frames.round());
            frames.throwBall(topplePin);
            OutputView.showScoreBoard(frames, player);
        }
    }
}
