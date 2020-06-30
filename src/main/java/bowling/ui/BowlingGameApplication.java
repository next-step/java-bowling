package bowling.ui;

import bowling.domain.BowlingGame;
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

        List<BowlingGame> bowlingGames = initGames(players);

        for (int i = 1; i < 11; i++) {
            playBowlingGame(i, bowlingGames);
        }
    }

    private static void playBowlingGame(int i, List<BowlingGame> bowlingGames) {
        if (i == 1) {
            doFirstThrow(bowlingGames);
        }
        if (i == 10) {
            doFinalFrame(bowlingGames);
        }
        if (i != 1 && i !=10) {
            doMiddleFrame(i, bowlingGames);
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

    private static List<BowlingGame> initGames(List<Player> players) {
        return players.stream()
                .map(BowlingGame::play)
                .collect(Collectors.toList());
    }

    private static void doFirstThrow(List<BowlingGame> bowlingGames) {
        for (BowlingGame bowlingGame : bowlingGames) {
            bowlingGame.bowlFirst(InputView.getNumberOfHitPin(1, bowlingGame.getPlayerName()));
            OutputView.printBowlingGames(bowlingGames);
            doNotCompletedFrame(1, bowlingGames, bowlingGame);
        }
    }

    private static void doMiddleFrame(int frameCount, List<BowlingGame> bowlingGames) {
        for (BowlingGame bowlingGame : bowlingGames) {
            bowlingGame.toNextFrame(InputView.getNumberOfHitPin(frameCount, bowlingGame.getPlayerName()));
            OutputView.printBowlingGames(bowlingGames);
            doNotCompletedFrame(frameCount, bowlingGames, bowlingGame);
        }
    }

    private static void doNotCompletedFrame(int frameCount, List<BowlingGame> bowlingGames, BowlingGame bowlingGame) {
        if (!bowlingGame.isCurrentFrameCompleted()) {
            bowlingGame.bowlCurrentFrame(InputView.getNumberOfHitPin(frameCount, bowlingGame.getPlayerName()));
            OutputView.printBowlingGames(bowlingGames);
        }
    }

    private static void doFinalFrame(List<BowlingGame> bowlingGames) {
        for (BowlingGame bowlingGame : bowlingGames) {
            bowlingGame.finalFrameBowlFirst(InputView.getNumberOfHitPin(10, bowlingGame.getPlayerName()));
            OutputView.printBowlingGames(bowlingGames);

            bowlingGame.finalFrameBowlSecond(InputView.getNumberOfHitPin(10, bowlingGame.getPlayerName()));
            OutputView.printBowlingGames(bowlingGames);

            bowlingGame.finalFrameBowlLast(InputView.getNumberOfHitPin(10, bowlingGame.getPlayerName()));
            OutputView.printBowlingGames(bowlingGames);
        }
    }
}
