package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.player.Player;
import bowling.view.FramesResult;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    public void start() {
        Player player = getPlayer();

        Frames frames = Frames.init();
        FramesResult framesResult = new FramesResult(player, frames);
        ResultView.printFrameResult(framesResult);


        while (!frames.isLast()) {
            Frame lastFrame = frames.lastFrame();

            while (lastFrame.canPitch()) {
                pitch(framesResult, lastFrame);
            }

            frames.addFrame();
        }
    }

    private void pitch(FramesResult framesResult, Frame lastFrame) {
        int number = InputView.inputPitchNumber(lastFrame.number());

        try {
            lastFrame.pitch(number);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }

        ResultView.printFrameResult(framesResult);
    }

    private Player getPlayer() {
        String playerName = InputView.inputPlayerName();

        try {
            return new Player(playerName);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getPlayer();
        }
    }
}
