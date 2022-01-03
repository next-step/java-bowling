package bowling;

import bowling.domain.Player;
import bowling.exception.PlayerLengthOutOfBoundException;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {

    public static void main(String[] args) throws PlayerLengthOutOfBoundException {
        String name = InputView.name();
        Player player = new Player(name);

        OutputView.result(player);
        while (!player.isFinished()) {
            int fallenPins = InputView.pins(player.currentRound());
            player.bowl(fallenPins);
            OutputView.result(player);
        }
    }

}
