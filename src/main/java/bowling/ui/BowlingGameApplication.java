package bowling.ui;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGameResult;
import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameApplication {
    public static void main(String[] args) {
        int numberOfPlayer = InputView.getNumberOfPlayer();

        List<Player> players = readyPlayers(numberOfPlayer);
        OutputView.printBowlingGameHeader();
        OutputView.printEmptyResults(players);

//        BowlingGame bowlingGame = BowlingGame.play(initPlayer);
//
//        firstBowl(bowlingGame);
//
//        bowlRemainFrames(bowlingGame);
//
//        bowlFinalFrame(bowlingGame);
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

    private static void firstBowl(BowlingGame bowlingGame) {
        List<BowlingGameResult> firstResults = bowlingGame.bowlFirst(InputView.getNumberOfHitPin(1));
        OutputView.printBowlingGameResult(bowlingGame.getPlayerName(), firstResults);
    }

    private static void bowlRemainFrames(BowlingGame bowlingGame) {
        for (int frameIndex = 1; frameIndex < 10; frameIndex++) {
            doNotCompletedFrame(bowlingGame, frameIndex);
            if (breakWhenFinalFrame(frameIndex)) break;
            doNextFrame(bowlingGame, frameIndex);
        }
    }

    private static void doNotCompletedFrame(BowlingGame bowlingGame, int frameIndex) {
        while (!bowlingGame.isCurrentFrameCompleted()) {
            List<BowlingGameResult> currentFrameResults =
                    bowlingGame.bowlCurrentFrame(InputView.getNumberOfHitPin(frameIndex));
            OutputView.printBowlingGameResult(bowlingGame.getPlayerName(), currentFrameResults);
        }
    }

    private static boolean breakWhenFinalFrame(int frameIndex) {
        return frameIndex == 9;
    }

    private static void doNextFrame(BowlingGame bowlingGame, int frameIndex) {
        List<BowlingGameResult> nextFrameResults =
                bowlingGame.toNextFrame(InputView.getNumberOfHitPin(frameIndex + 1));
        OutputView.printBowlingGameResult(bowlingGame.getPlayerName(), nextFrameResults);
    }

    private static void bowlFinalFrame(BowlingGame bowlingGame) {
        List<BowlingGameResult> finalFirstThrown =
                bowlingGame.finalFrameBowlFirst(InputView.getNumberOfHitPin(10));
        OutputView.printBowlingGameResult(bowlingGame.getPlayerName(), finalFirstThrown);

        List<BowlingGameResult> finalSecondThrown =
                bowlingGame.finalFrameBowlSecond(InputView.getNumberOfHitPin(10));
        OutputView.printBowlingGameResult(bowlingGame.getPlayerName(), finalSecondThrown);

        if (!bowlingGame.isCurrentFrameCompleted()) {
            List<BowlingGameResult> lastThrown
                    = bowlingGame.finalFrameBowlLast(InputView.getNumberOfHitPin(10));
            OutputView.printBowlingGameResult(bowlingGame.getPlayerName(), lastThrown);
        }
    }
}
