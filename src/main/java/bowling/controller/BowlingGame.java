package bowling.controller;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerCount;
import bowling.domain.player.Players;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final InputView inputView;

    public BowlingGame(final InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        Players players = join();
        showOverHead(players);
        while (players.isPlaying()) {
            play(players);
        }
    }

    private Players join() {
        final PlayerCount playerCount = inputView.inputPlayerCount();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= playerCount.getPlayerCount(); i++) {
            players.add(inputView.inputPlayerName(i));
        }
        return new Players(players);
    }

    private void play(final Players players) {
        for (Player player : players.getPlayers()) {
            bowl(player, players);
            player.waitNextFrame();
        }
    }

    private void bowl(final Player player, final Players players) {
        Pins pins = Pins.of();
        while (!player.isFinish()) {
            BowlCount bowlCount = inputView.inputBowlCount(player.getName());
            player.bowl(pins.knockOver(bowlCount));
            showOverHead(players);
        }
    }

    private void showOverHead(final Players players) {
        OutputView.printFramesHeader();
        for (Player player : players.getPlayers()) {
            OutputView.printOverHead(player.getName(), player.getStates(), player.getScores());
        }
    }
}
