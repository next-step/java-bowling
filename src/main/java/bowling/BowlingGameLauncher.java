package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameLauncher {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        String name = inputView.requestPlayer();

        BowlingGame bowlingGame = BowlingGame.newInstance();
        OutputView outputView = new OutputView();

        while (!bowlingGame.isFinished()) {
            int framePosition = bowlingGame.getFramePosition();
            int downPin = inputView.requestDownPin(framePosition);
            bowlingGame.play(downPin);
            outputView.printResult(name, bowlingGame.getResult());
        }
    }
}
