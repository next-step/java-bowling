package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameLauncher {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        String name = inputView.requestPlayer();

        BowlingGame bowlingGame = BowlingGame.newInstance(1);
        OutputView outputView = new OutputView();

        while (!bowlingGame.isFinished(0)) {
            int framePosition = bowlingGame.getFramePosition(0);
            int downPin = inputView.requestDownPin(framePosition);
            bowlingGame.play(0,downPin);
            outputView.printResult(name, bowlingGame.getResult().get(0));
        }
    }
}
