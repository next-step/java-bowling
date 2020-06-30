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

        for (int i = 1; i < 11; i++) {
            playBowlingGame(i, bowlingGamePlayers);
        }
    }

    private static void playBowlingGame(int i, List<BowlingGamePlayer> bowlingGamePlayers) {
        if (i == 1) {
            doFirstThrow(bowlingGamePlayers);
        }
        if (i == 10) {
            doFinalFrame(bowlingGamePlayers);
        }
        if (i != 1 && i !=10) {
            doMiddleFrame(i, bowlingGamePlayers);
        }
    }

    private static List<Player> readyPlayers(int numberOfPlayer) {
        List<Player> players = new ArrayList<>();
        for (int count = 0; count < numberOfPlayer; count ++) {
           players.add(readyPlayer());
        }
        return players;
    }

    private static Player readyPlayer() {
        String userName = InputView.getPlayerName();

        return Player.createByName(userName);
    }

    private static List<BowlingGamePlayer> initGames(List<Player> players) {
        return players.stream()
                .map(BowlingGamePlayer::play)
                .collect(Collectors.toList());
    }

    private static void doFirstThrow(List<BowlingGamePlayer> bowlingGamePlayers) {
        for (BowlingGamePlayer bowlingGamePlayer : bowlingGamePlayers) {
            bowlingGamePlayer.bowlFirst(InputView.getNumberOfHitPin(1, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);
            doNotCompletedFrame(1, bowlingGamePlayers, bowlingGamePlayer);
        }
    }

    private static void doMiddleFrame(int frameCount, List<BowlingGamePlayer> bowlingGamePlayers) {
        for (BowlingGamePlayer bowlingGamePlayer : bowlingGamePlayers) {
            bowlingGamePlayer.toNextFrame(InputView.getNumberOfHitPin(frameCount, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);
            doNotCompletedFrame(frameCount, bowlingGamePlayers, bowlingGamePlayer);
        }
    }

    private static void doNotCompletedFrame(int frameCount, List<BowlingGamePlayer> bowlingGamePlayers, BowlingGamePlayer bowlingGamePlayer) {
        if (!bowlingGamePlayer.isCurrentFrameCompleted()) {
            bowlingGamePlayer.bowlCurrentFrame(InputView.getNumberOfHitPin(frameCount, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);
        }
    }

    private static void doFinalFrame(List<BowlingGamePlayer> bowlingGamePlayers) {
        for (BowlingGamePlayer bowlingGamePlayer : bowlingGamePlayers) {
            bowlingGamePlayer.finalFrameBowlFirst(InputView.getNumberOfHitPin(10, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);

            bowlingGamePlayer.finalFrameBowlSecond(InputView.getNumberOfHitPin(10, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);

            bowlingGamePlayer.finalFrameBowlLast(InputView.getNumberOfHitPin(10, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);
        }
    }
}
