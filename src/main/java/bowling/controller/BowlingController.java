package bowling.controller;

import bowling.domain.player.Players;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {
    public void start() {
        Players players = createPlayers();

        ResultView.printFrameResults(players);

        while (!players.isAllEnd()) {
            bowlAndPrintResult(players);
        }
    }

    private Players createPlayers() {
        int numberOfPlayers = InputView.inputNumberOfPlayers();
        List<Player> players = IntStream.range(0, numberOfPlayers)
                .mapToObj(i -> createPlayer(i + 1))
                .collect(Collectors.toList());

        try {
            return new Players(players);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return createPlayers();
        }
    }

    private Player createPlayer(int index) {
        String playerName = InputView.inputPlayerName(index);

        try {
            return new Player(playerName);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return createPlayer(index);
        }
    }

    private void bowlAndPrintResult(Players players) {
        players.getPlayers()
                .stream()
                .filter(Player::canBowl)
                .forEach(player -> {
                    bowl(player);
                    ResultView.printFrameResults(players);
                });
    }

    private void bowl(Player player) {
        int number = InputView.inputBowlNumber(player.getName().getValue());

        try {
            player.bowl(number);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            bowl(player);
        }
    }
}
