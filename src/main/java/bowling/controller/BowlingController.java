package bowling.controller;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.domain.frame.Frames;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingController {

    private static final int FINAL_ROUND = 10;
    private Players players;

    public void run() {
        players = initPlayers();
        OutputView.showScoreBoard(players);
        IntStream.range(0, FINAL_ROUND)
                .forEach(this::playRound);
    }

    private void playRound(int round) {
        IntStream.range(0, players.howManyPlayers())
                .forEach(n->playFrame(round, n));
    }

    private void playFrame(int round, int n) {
        while (!players.nthPlayerFrameEnd(n, round)) {
            int topplePin = InputView.getTopplePin(players.nthPlayer(n));
            players.nthPlayerThrowBall(n, topplePin);
            addScore(players.nthPlayer(n), players.playerFrames(n));
            OutputView.showScoreBoard(players);
        }
    }

    private Players initPlayers() {
        int playerCount = InputView.getPlayerCount();
        return IntStream.rangeClosed(1, playerCount)
                .mapToObj(InputView::getPlayerName)
                .map(Player::from)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::from));
    }

    private void addScore(Player player, Frames frames) {
        if (frames.canCalculateScore(player.scoreRound())) {
            player.addScore(frames.getScore(player.scoreRound()));
        }
    }
}
