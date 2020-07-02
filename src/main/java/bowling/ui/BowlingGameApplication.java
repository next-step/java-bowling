package bowling.ui;

import bowling.domain.BowlingGamePlayer;
import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameApplication {
    public static void main(String[] args) {
        int numberOfPlayer = InputView.getNumberOfPlayer();

        List<Player> players = readyPlayers(numberOfPlayer);
        OutputView.printBowlingGameHeader();
        OutputView.printEmptyResults(players);

        List<BowlingGamePlayer> bowlingGamePlayers = initGames(players);
        BowlingGame bowlingGame = BowlingGame.of(bowlingGamePlayers);

        for (int i = 1; i < 11; i++) {
            bowlingGame.playBowlingGame(i);
        }
    }

    private static List<Player> readyPlayers(int numberOfPlayer) {
        List<Player> players = new ArrayList<>();
        for (int count = 0; count < numberOfPlayer; count ++) {
           players.add(readyPlayer(count));
        }
        return players;
    }

    private static Player readyPlayer(int count) {
        String userName = InputView.getPlayerName(count + 1);

        return Player.createByName(userName);
    }

    private static List<BowlingGamePlayer> initGames(List<Player> players) {
        return players.stream()
                .map(BowlingGamePlayer::play)
                .collect(Collectors.toList());
    }
}
