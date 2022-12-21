package bowling.controller;

import bowling.model.Pin;
import bowling.model.Player;
import bowling.model.frame.Frames;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public void run() {
        Player player = new Player(InputView.inputPlayerName());
        Frames frames = new Frames();
        OutputView.printBowlResult(player, frames);

        while (!frames.isGameOver()) {
            Pin pin = Pin.of(InputView.inputPinNumber(frames.getCurrentFrameNumber()));
            frames.bowl(pin);
            frames.nextFrame();
            OutputView.printBowlResult(player, frames);
        }
    }
}
