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
            inputBowlNumberAndPrintResult(framesResult, frames);
        }
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

    private void inputBowlNumberAndPrintResult(FramesResult framesResult, Frames frames) {
        Frame lastFrame = frames.lastFrame();

        while (lastFrame.canBowl()) {
            bowl(lastFrame);
            ResultView.printFrameResult(framesResult);
        }

        frames.addFrame();
    }

    private void bowl(Frame frame) {
        int number = InputView.inputBowlNumber(frame.getFrameNumber());

        try {
            frame.bowl(number);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
