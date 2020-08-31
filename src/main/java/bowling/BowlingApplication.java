package bowling;

import bowling.domian.player.Player;
import bowling.domian.player.Players;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        Players players = InputView.getPlayers();

        ResultView.printFrames(players);
        while (!players.isGameEnd()) {
            play(players);
        }

        ResultView.printGameEnd();
    }

    private static void play(Players players) {
        for (Player player : players.getCurrentPlayers()) {
            play(player, players);
        }
    }

    private static void play(Player player, Players players) {
        int falledPinsCount = InputView.getFalledPinsCount(player.getName());
        player.bowl(falledPinsCount);

        ResultView.printFrames(players);
    }
}
