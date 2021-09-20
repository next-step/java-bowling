package bowling.bowlingplayers;

import bowling.bowlingplayers.domain.Player;
import bowling.bowlingplayers.view.InputView;
import bowling.bowlingplayers.view.ResultView;

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
