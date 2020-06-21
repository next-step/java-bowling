package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {

    public static void main(String[] args) {
        String playerName = InputView.inputPlayerName();
        OutputView.outputDefaultFrame(playerName);

        BowlingGame bowlingGame = new BowlingGame(playerName);

        while (!bowlingGame.gameOver()) {
            int point = InputView.inputScore(bowlingGame.getFameSize());
            bowlingGame.addPoint(point);

            OutputView.outputFrames(bowlingGame.getFrames());
        }

    }
}
