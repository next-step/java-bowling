package bowling.controller;

import bowling.domain.Frames;
import bowling.domain.Name;
import bowling.domain.frame.Frame;
import bowling.view.InputView;

public class BowlingGameController {
    public void startGame() {
        Name playerName = new Name(InputView.inputPlayerName());
        Frames frames = Frames.init();
        while (!frames.isFramesEnd()) {

        }
    }
}
