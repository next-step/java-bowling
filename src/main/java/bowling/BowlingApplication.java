package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {

    public static void main(String[] args) {
        String playerName = InputView.inputPlayerName();
        OutputView.outputDefaultFrame(playerName);

        BowlingGame bowlingGame = new BowlingGame(playerName);

        while (!bowlingGame.isGameOver()) {
            bowlingGame.addNextFrame();
            addPoint(bowlingGame);
        }

    }

    private static void addPoint(BowlingGame bowlingGame) {
        int frameIndex = bowlingGame.currentPlayFrameIndex();
        Frame frame = bowlingGame.findCurrentFrame();
        while (frame.availablePlay()) {
            int point = InputView.inputScore(frameIndex);
            frame.addPoint(point);
            OutputView.outputFrames(bowlingGame.getFrames());
        }
    }
}
