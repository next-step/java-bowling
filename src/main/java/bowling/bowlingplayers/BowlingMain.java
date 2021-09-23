package bowling.bowlingplayers;

import bowling.bowlingplayers.domain.Player;
import bowling.bowlingplayers.domain.Players;
import bowling.bowlingplayers.view.InputView;
import bowling.bowlingplayers.view.ResultView;

import java.util.List;

public class BowlingMain {

    public static void main(String[] args) {
        List<String> playersNames = InputView.inputPlayers();
        Players players = new Players(playersNames);
        ResultView.showPlayBoard(players);

        while (!players.end()) {
            Player player = players.pitchingPlayer();
            int pins = InputView.inputPins(player);
            player.pitch(pins);
            ResultView.showPlayBoard(players);
        }
    }
}
