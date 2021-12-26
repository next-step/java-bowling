package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public void start() {
        Player player = new Player(InputView.printInputPlayer());
        Frames frames = new Frames();
        ResultView.printEmptyFrame(player);

        while (frames.isNotFinalGame()) {
            ResultView.printFrameNumber(frames.getFrameNumber());
            frames.addHittingPinsAtCurrentFrame(InputView.printInputHittingPins());
            ResultView.printScoreFrame(player, frames);
            frames.moveNextFrame();
        }
    }

}
