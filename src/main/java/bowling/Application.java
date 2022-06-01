package bowling;

import bowling.domain.game.Game;
import bowling.domain.game.Games;
import bowling.domain.player.Player;
import bowling.domain.state.Pin;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        int playerCount = InputView.inputPlayerCount();

        List<Player> players = generatePlayers(playerCount);
        Games games = generateGames(players);
        playGames(games);
        OutputView.printGameIsDone();
    }

    private static List<Player> generatePlayers(int playerCount) {
        List<Player> players = new ArrayList<>();

        for (int i = 1; i < playerCount + 1; i++) {
            players.add(new Player(InputView.inputPlayerName(i)));
        }

        return players;
    }

    private static Games generateGames(List<Player> players) {
        return new Games(players.stream()
                .map(Game::enter)
                .collect(Collectors.toList()));
    }

    private static void playGames(Games games) {
        while (!games.isDone()) {
            games.bowl(inputPinCount(games.whoseTurn()));
            OutputView.printBoard(games);
        }
    }

    private static Pin inputPinCount(Player player) {
        return new Pin(InputView.inputPinCount(player));
    }
}
