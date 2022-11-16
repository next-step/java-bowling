package bowling;

import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Username username = InputView.playerName();

        BowlingGame bowlingGame = new BowlingGame();
        FrameNumber frameNumber = FrameNumber.first();
        while (frameNumber.isBelow(BowlingGame.FRAME_SIZE)) {
            int count = InputView.hitCount(frameNumber.getFrameNumber());

            frameNumber = bowlingGame.pitchingBall(count);

            Frames frames = bowlingGame.getFrames();
            OutputView.printScoreScreen(username, frames, bowlingGame);
        }
    }
}
