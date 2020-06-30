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

        doFirstThrow(bowlingGames);

        doMiddleThrows(bowlingGames);

        doFinalThrows(bowlingGames);
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
        }
    }

    private static void doMiddleThrows(List<BowlingGame> bowlingGames) {
        for (int frameIndex = 1; frameIndex < 10; frameIndex++) {
            doNotCompletedMiddleFrames(bowlingGames, frameIndex);
            if (breakWhenFinalFrame(frameIndex)) break;
            doNextFrames(bowlingGames, frameIndex);
        }
    }

    private static void doNextFrames(List<BowlingGame> bowlingGames, int frameIndex) {
        for (BowlingGame bowlingGame : bowlingGames) {
            doNextFrame(bowlingGame, frameIndex);
            OutputView.printBowlingGames(bowlingGames);
        }
    }

    private static void doNotCompletedMiddleFrames(List<BowlingGame> bowlingGames, int frameIndex) {
        for (BowlingGame bowlingGame : bowlingGames) {
            doNotCompletedFrame(bowlingGame, frameIndex);
            OutputView.printBowlingGames(bowlingGames);
        }
    }

    private static void doNotCompletedFrame(BowlingGame bowlingGame, int frameIndex) {
        while (!bowlingGame.isCurrentFrameCompleted()) {
            bowlingGame.bowlCurrentFrame(InputView.getNumberOfHitPin(frameIndex, bowlingGame.getPlayerName()));
        }
    }

    private static boolean breakWhenFinalFrame(int frameIndex) {
        return frameIndex == 9;
    }

    private static void doNextFrame(BowlingGame bowlingGame, int frameIndex) {
        bowlingGame.toNextFrame(InputView.getNumberOfHitPin(frameIndex + 1, bowlingGame.getPlayerName()));
    }

    private static void doFinalThrows(List<BowlingGame> bowlingGames) {
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
