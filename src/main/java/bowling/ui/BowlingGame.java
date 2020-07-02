package bowling.ui;

import bowling.domain.BowlingGamePlayer;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final List<BowlingGamePlayer> bowlingGamePlayers;

    public BowlingGame(List<BowlingGamePlayer> bowlingGamePlayers) {
        this.bowlingGamePlayers = new ArrayList<>(bowlingGamePlayers);
    }

    public static BowlingGame of(List<BowlingGamePlayer> bowlingGamePlayerList) {
        return new BowlingGame(bowlingGamePlayerList);
    }

    public void playBowlingGame(int frameIndex) {
        if (frameIndex == 1) {
            doFirstThrow();
        }
        if (frameIndex == 10) {
            doFinalFrame();
        }
        if (frameIndex != 1 && frameIndex !=10) {
            doMiddleFrame(frameIndex);
        }
    }

    private void doFirstThrow() {
        for (BowlingGamePlayer bowlingGamePlayer : bowlingGamePlayers) {
            bowlingGamePlayer.bowlFirst(InputView.getNumberOfHitPin(1, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);

            doNotCompletedFrame(1, bowlingGamePlayer);
        }
    }

    private void doMiddleFrame(int frameCount) {
        for (BowlingGamePlayer bowlingGamePlayer : bowlingGamePlayers) {
            bowlingGamePlayer.toNextFrame(InputView.getNumberOfHitPin(frameCount, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);

            doNotCompletedFrame(frameCount, bowlingGamePlayer);
        }
    }

    private void doNotCompletedFrame(int frameCount, BowlingGamePlayer bowlingGamePlayer) {
        if (!bowlingGamePlayer.isCurrentFrameCompleted()) {
            bowlingGamePlayer.bowlCurrentFrame(
                    InputView.getNumberOfHitPin(frameCount, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);
        }
    }

    private void doFinalFrame() {
        for (BowlingGamePlayer bowlingGamePlayer : bowlingGamePlayers) {
            bowlingGamePlayer.finalFrameBowlFirst(
                    InputView.getNumberOfHitPin(10, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);

            bowlingGamePlayer.finalFrameBowlSecond(
                    InputView.getNumberOfHitPin(10, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);

            bowlingGamePlayer.finalFrameBowlLast(
                    InputView.getNumberOfHitPin(10, bowlingGamePlayer.getPlayerName()));
            OutputView.printBowlingGames(bowlingGamePlayers);
        }
    }
}
