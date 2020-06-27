package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final Player player;
    private List<BowlingGameResult> bowlingGameResults;

    public BowlingGame(Player player, List<BowlingGameResult> bowlingGameResults) {
        this.player = player;
        this.bowlingGameResults = new ArrayList<>(bowlingGameResults);
    }

    public static BowlingGame play(Player player) {
        return new BowlingGame(player, new ArrayList<>());
    }

    public static BowlingGame start(String playerName) {
        return new BowlingGame(Player.createByName(playerName), new ArrayList<>());
    }

    public int checkWhereFrame() {
        return this.bowlingGameResults.size();
    }

    public List<BowlingGameResult> bowlFirst(int numberOfHitPin) {
        Frame frame = player.bowlFirstRefactor(numberOfHitPin);

        BowlingGameResult bowlingGameResult = new BowlingGameResult(
                frame.calculateCurrentResults(),
                frame.calculateCurrentScore()
        );
        this.bowlingGameResults.add(bowlingGameResult);

        return new ArrayList<>(this.bowlingGameResults);
    }

    public List<BowlingGameResult> bowlCurrentFrame(int numberOfHitPin) {
        Frame frame = player.bowlCurrentFrameRefactor(numberOfHitPin);

        BowlingGameResult bowlingGameResult = new BowlingGameResult(
                frame.calculateCurrentResults(),
                frame.calculateCurrentScore()
        );

        this.bowlingGameResults.set(lastIndexOfBowlingGameResults(), bowlingGameResult);
        applyBonusToPreviousFrame(frame);

        return new ArrayList<>(this.bowlingGameResults);
    }

    public List<BowlingGameResult> toNextFrame(int numberOfHitPin) {
        Frame frame = player.toNextFrameRefactor(numberOfHitPin);

        BowlingGameResult bowlingGameResult = new BowlingGameResult(
                frame.calculateCurrentResults(),
                frame.calculateCurrentScore()
        );

        this.bowlingGameResults.add(bowlingGameResult);
        applyBonusToPreviousFrame(frame);

        return new ArrayList<>(this.bowlingGameResults);
    }

    private void applyBonusToPreviousFrame(Frame frame) {
        if (lastIndexOfBowlingGameResults() != 0) {
            BowlingGameResult previousBowlingGameResult =
                    this.bowlingGameResults.get(lastIndexOfBowlingGameResults() - 1);
            BowlingGameResult bonusApplied = previousBowlingGameResult.applyBonus(frame.calculatePreviousScore());
            this.bowlingGameResults.set(lastIndexOfBowlingGameResults() - 1, bonusApplied);
        }
    }

    public boolean isCurrentFrameCompleted() {
        return this.player.isCurrentFrameCompleted();
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    private int lastIndexOfBowlingGameResults() {
        return this.bowlingGameResults.size() - 1;
    }
}
