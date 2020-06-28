package bowling.ui;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGameResult;
import bowling.domain.Player;

import java.util.List;

public class BowlingGameApplication {
    public static void main(String[] args) {
        Player initPlayer = initGame();

        BowlingGame bowlingGame = BowlingGame.play(initPlayer);

        firstBowl(bowlingGame);

        bowlRemainFrames(bowlingGame);
    }

    private static Player initGame() {
        String userName = InputView.getPlayerName();

        OutputView.printEmptyResult(userName);

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
}
