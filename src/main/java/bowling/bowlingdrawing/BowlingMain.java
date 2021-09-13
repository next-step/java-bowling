package bowling.bowlingdrawing;

import bowling.bowlingdrawing.domain.Player;
import bowling.bowlingdrawing.view.InputView;
import bowling.bowlingdrawing.view.ResultView;

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
