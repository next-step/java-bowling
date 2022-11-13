package bowling;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
    public static void main(String[] args) {
        Player player = new Player(InputView.scanName());
        for (int i = 0; i < 10; i++) {
            startFrame(player, i);
        }
    }

    private static void startFrame(Player player, int index) {
        while (player.process(index)) {
            Pin pin = new Pin(InputView.scanScore(index));
            player.pitch(index, pin);
            OutputView.printScores(player);
        }
    }
}
