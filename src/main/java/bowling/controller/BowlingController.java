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
        Players players = getPlayers();

        ResultView.printFrameResults(players);

        while (!players.isAllEnd()) {
            inputBowlNumberAndPrintResult(players);
        }
    }

    private Players getPlayers() {
        int numberOfPlayers = InputView.inputNumberOfPlayers();
        List<Player> players = IntStream.rangeClosed(1, numberOfPlayers)
                .mapToObj(this::getPlayer)
                .collect(Collectors.toList());

        try {
            return new Players(players);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getPlayers();
        }
    }

    private Player getPlayer(int index) {
        String playerName = InputView.inputPlayerName(index);

        try {
            return new Player(playerName);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getPlayer(index);
        }
    }

    private void inputBowlNumberAndPrintResult(Players players) {
        for (Player player : players.getPlayers()) {
            inputBowlNumber(player, players);
        }
    }

    private void inputBowlNumber(Player player, Players players) {
        if (player.canBowl()) {
            bowl(player);
            ResultView.printFrameResults(players);
        }
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
