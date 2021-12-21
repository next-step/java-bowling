package bowling.controller;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Controller {
    public void run() {
        Player name = new Player(InputView.InputPlayerName());
        Frames frames = new Frames();
        OutputView.printScoreBoard(name);

        while (frames.isNotFinalFrame()) {
            frames.add(InputView.InputFallenPin(frames.frameNumber()));
            OutputView.printFrame(name, frames);
            frames.nextFrame();
        }
    }

}
