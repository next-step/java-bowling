package bowling.controller;

import bowling.domain.Player;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;


public class Controller {

    public static void startBowling() {
        String player = InputView.inputUserNames();

        Frames frames = Frames.init();
        while (!frames.isLastFrame()) {
            int pinCount = InputView.inputPinCount(frames.getCurrentRound());
            frames.pitch(pinCount);
            ResultView.printScore(Player.get(player).getPlayer(), frames.getFrames());
        }
    }
}
