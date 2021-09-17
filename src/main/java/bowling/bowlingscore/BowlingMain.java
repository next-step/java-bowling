package bowling.bowlingscore;

import bowling.bowlingscore.domain.Player;
import bowling.bowlingscore.view.InputView;
import bowling.bowlingscore.view.ResultView;

public class BowlingMain {

    public static void main(String[] args) {
        String playerName = InputView.inputPlayer();
        Player player = new Player(playerName);
        ResultView.showPlayBoard(player);

        while (!player.end()) {
            int pins = InputView.inputPins(player);
            player.pitch(pins);
            ResultView.showPlayBoard(player);
        }
    }
}
