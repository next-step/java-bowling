package bowling;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.scanName());
        OutputView.printScores(player);
        for (int i = 0; i < 10; i++) {
            startFrame(player, i);
        }
    }

    private static void startFrame(Player player, int index) {
        while (player.process(index)) {
            Pin pin = new Pin(InputView.scanScore(index + 1));
            player.knockedDownPins(pin);
            OutputView.printScores(player);
        }
    }
}
