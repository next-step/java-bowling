package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        String playerName = inputView.inputPlayerName();

        BowlingGame bowlingGame = new BowlingGame(playerName);

        OutputView.printScoreBoard(playerName, bowlingGame.getPlayer().getFrames());

        do {
            int lastFrameNumber = bowlingGame.getLastFrameNumber();
            int clearPinCount = inputView.inputClearPin(lastFrameNumber);

            bowlingGame.play(clearPinCount);

            OutputView.printScoreBoard(playerName, bowlingGame.getPlayer().getFrames());
        } while (!bowlingGame.isEnd());
    }
}
