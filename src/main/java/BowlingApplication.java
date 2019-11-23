import bowling.BowlingGame;
import view.InputView;
import view.ResultView;

import static bowling.Frames.FRAME_COUNT;

public class BowlingApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayer();
        BowlingGame bowlingGame = new BowlingGame(name);
        ResultView.printInitialScoreBoard(bowlingGame);
        int frameIndex = 1;
        while (frameIndex <= FRAME_COUNT) {
            int score = InputView.inputFrame(frameIndex);
            bowlingGame.roll(score);
            frameIndex = bowlingGame.getFrames().lastOfFrames();
            ResultView.printScoreBoard(bowlingGame);
        }
    }
}
