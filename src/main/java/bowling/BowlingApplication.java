package bowling;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.domain.Players;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {
    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();

        Players players = new Players();
        for (int i = 1; i <= playerCount; i++) {
            players.addPlayer(new Player(InputView.inputPlayerName(i)));
        }

        OutputView.printBoard(players.getPlayers());

        while (players.areNotFinished()) {
            Pin pin = new Pin(InputView.inputBowl(players.getNextPlayerName()));
            players.bowl(pin);
            OutputView.printBoard(players.getPlayers());
        }

    }
}
