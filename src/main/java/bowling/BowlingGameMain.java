package bowling;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameMain {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Player player = inputView.inputPlayerName();

        for (int index = 0; index < 10; index++) {
            Pin pin = inputView.inputBowlHitPin(index);

            Frame frame = player.play(index, pin);
            outputView.printResult(player);

            while (frame.isOpened()) {
                pin = inputView.inputBowlHitPin(index);
                frame = player.play(index, pin);
                outputView.printResult(player);
            }
        }

    }
}
