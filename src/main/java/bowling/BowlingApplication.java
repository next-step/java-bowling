package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {

    public static void main(String[] args) {
        String playerName = InputView.inputPlayerName();
        OutputView.outputDefaultFrame(playerName);

        BowlingGame bowlingGame = new BowlingGame(playerName);

        while (!bowlingGame.gameOver()) {
            Frames frames = bowlingGame.getFrames();
            int point = InputView.inputScore(frames.getFrameSize());
            bowlingGame.addPoint(point);

            OutputView.outputFrames(bowlingGame.getFrames());
        }

    }
}
