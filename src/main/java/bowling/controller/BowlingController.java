package bowling.controller;

import bowling.model.Pin;
import bowling.model.Player;
import bowling.model.frame.Frames;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public void run(){
        Player player = new Player(InputView.inputPlayerName());
        Frames frames = new Frames();
        OutputView.printBowlResult(player, frames);

       InputView.inputPinNumber(frames.getCurrentFrameNumber());

    }
}
