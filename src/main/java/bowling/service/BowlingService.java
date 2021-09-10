package bowling.service;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingService {

    private static final int FINAL_FRAME = 10;
    private static final int FIRST_FRAME = 1;

    public static void playBowling() {
        String userName = InputView.inputName();
        Player player = new Player(userName);
        ResultView.printScoreBoard(player);
        play(player);
    }

    private static void play(Player player) {
        player.createEmptyFrame();
        for (int frameIndex = FIRST_FRAME; frameIndex < FINAL_FRAME; frameIndex++) {
            playBall(player);
        }
        playFinalBall(player);
    }

    private static Frame playBall(Player player) {
        int currentFrameIndex = player.getFrameIndex();
        int hitNumberOfPin = InputView.hitNumberOfPin(currentFrameIndex);
        Frame frame = player.firstBall(hitNumberOfPin);
        ResultView.printScoreBoard(player);

        if (!frame.isStrike()) {
            hitNumberOfPin = InputView.hitNumberOfPin(currentFrameIndex);
            frame.secondBall(hitNumberOfPin);
            ResultView.printScoreBoard(player);
        }

        return frame;
    }

    private static void playFinalBall(Player player) {
        int currentFrameIndex = player.getFrameIndex();
        int hitNumberOfPin = InputView.hitNumberOfPin(currentFrameIndex);
        FinalFrame finalFrame = player.finalFrame(hitNumberOfPin);
        ResultView.printScoreBoard(player);

        if (!finalFrame.isStrike()) {
            hitNumberOfPin = InputView.hitNumberOfPin(currentFrameIndex);
            finalFrame.secondBall(hitNumberOfPin);
            ResultView.printScoreBoard(player);
        }

        if (finalFrame.isStrike() || finalFrame.isSpare()) {
            hitNumberOfPin = InputView.hitNumberOfPin(currentFrameIndex);
            finalFrame.finalBall(hitNumberOfPin);
            ResultView.printScoreBoard(player);
        }
    }
}
