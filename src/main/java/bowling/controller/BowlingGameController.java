package bowling.controller;

import bowling.domain.Player;
import bowling.domain.frame.BowlingFrames;
import bowling.domain.frame.Frame;
import bowling.domain.pin.FallenPins;
import bowling.view.InputView;

public class BowlingGameController {

    public static void main(String[] args) {
        play();
    }

    public static void play() {
        Player player = InputView.getPlayer();

        BowlingFrames bowlingFrames = new BowlingFrames();

        for (int i = 0; i < BowlingFrames.LAST_FRAME; i++) {
            Frame frame = bowlingFrames.getFrame(i);
            bowl(player, bowlingFrames, i, frame);
        }

    }

    private static void bowl(Player player, BowlingFrames bowlingFrames, int i, Frame frame) {
        while (!frame.isFinish()) {
            FallenPins fallenPins = InputView.getFallenPins(i + 1);
            player.bowlBall(frame, fallenPins);
            //ResultView.printFrame(bowlingFrames, player);
        }
    }

}
