package bowling.service;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingService {
    public static void playBowling() {
        String userName = InputView.inputName();
        Player player = new Player(userName);
        ResultView.printScoreBoard(player);
        play(player);
    }

    private static void play(Player player) {
        NormalFrame frame = (NormalFrame) player.startFrame();

        for (int frameIndex = Frame.FIRST_FRAME; frameIndex < Frame.LAST_FRAME; frameIndex++) {
            frame = playBall(player, frame);
        }
        playFinalBall(player, frame);
    }

    private static NormalFrame playBall(Player player, NormalFrame frame) {
        int nextFrameIndex = frame.nextFrameIndex();
        int hitNumberOfPin = InputView.hitNumberOfPin(nextFrameIndex);

        NormalFrame nextFrame = (NormalFrame) frame.nextFrame(hitNumberOfPin);
        player.addFrame(nextFrame);
        nextFrame.calculateFrame();
        ResultView.printScoreBoard(player);

        if (nextFrame.isStrike()) {
            return nextFrame;
        }

        hitNumberOfPin = InputView.hitNumberOfPin(nextFrameIndex);
        nextFrame.secondBall(hitNumberOfPin);
        nextFrame.calculateFrame();
        ResultView.printScoreBoard(player);

        return nextFrame;
    }

    private static void playFinalBall(Player player, NormalFrame frame) {
        int currentFrameIndex = frame.frameIndex() + 1;
        int hitNumberOfPin = InputView.hitNumberOfPin(currentFrameIndex);

        FinalFrame finalFrame = (FinalFrame) frame.nextFrame(hitNumberOfPin);
        player.addFrame(finalFrame);
        finalFrame.calculateFrame();
        ResultView.printScoreBoard(player);

        hitNumberOfPin = InputView.hitNumberOfPin(currentFrameIndex);
        finalFrame.secondBall(hitNumberOfPin);
        finalFrame.calculateFrame();
        ResultView.printScoreBoard(player);

        if (finalFrame.isStrike() || finalFrame.isSpare()) {
            hitNumberOfPin = InputView.hitNumberOfPin(currentFrameIndex);
            finalFrame.finalBall(hitNumberOfPin);
            finalFrame.calculateFrame();
            ResultView.printScoreBoard(player);
        }
    }
}
