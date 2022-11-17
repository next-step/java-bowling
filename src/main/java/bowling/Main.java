package bowling;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int count = InputView.scanCountOfPlayers();
        List<Player> players = enterPlayers(count);
        OutputView.printResult(players);
        for (int i = 0; i < 10; i++) {
            startFrame(players);
        }
    }

    private static List<Player> enterPlayers(int count) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            players.add(new Player(InputView.scanName(i)));
        }
        return players;
    }

    private static void startFrame(List<Player> players) {
        for (Player player : players) {
            startPlayerTurn(players, player);
        }
    }

    private static void startPlayerTurn(List<Player> players, Player player) {
        while (player.process()) {
            Pin pin = new Pin(InputView.scanScore(player));
            player.knockedDownPins(pin);
            OutputView.printResult(players);
        }
    }
}
