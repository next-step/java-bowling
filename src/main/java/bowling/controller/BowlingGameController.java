package bowling.controller;

import bowling.domain.Name;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public void startGame() {
        Name player = new Name(InputView.inputPlayerName());
        Frames frames = Frames.init();

        ResultView.printInit(player);
        while (!frames.isFramesEnd()) {
            int countOfPins = InputView.inputFramePitching(frames);
            frames.pitch(countOfPins);
            ResultView.printResult(player, frames);
        }
    }
}
