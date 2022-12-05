package bowling;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {
    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        OutputView.printBoard(player);

        boolean isNotFinished = true;
        while (isNotFinished) {
            Pin pin = new Pin(InputView.inputBowl(player.getFrameNumber()));
            isNotFinished = player.bowl(pin);
            OutputView.printBoard(player);
        }

    }
}
