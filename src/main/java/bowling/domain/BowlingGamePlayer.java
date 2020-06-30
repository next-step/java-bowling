package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class BowlingGamePlayer {
    private final Player player;
    private List<BowlingGameResult> bowlingGameResults;

    public BowlingGamePlayer(Player player, List<BowlingGameResult> bowlingGameResults) {
        this.player = player;
        this.bowlingGameResults = new ArrayList<>(bowlingGameResults);
    }

    public static BowlingGamePlayer play(Player player) {
        return new BowlingGamePlayer(player, new ArrayList<>());
    }

    public static BowlingGamePlayer start(String playerName) {
        return new BowlingGamePlayer(Player.createByName(playerName), new ArrayList<>());
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
        applySpecialStrikeBonus(frame);

        return new ArrayList<>(this.bowlingGameResults);
    }

    public List<BowlingGameResult> finalFrameBowlFirst(int numberOfHitPin) {
        Frame frame = player.toNextFrameRefactor(numberOfHitPin);

        this.bowlingGameResults.add(new BowlingGameResult(
                frame.calculateCurrentResults(),
                frame.calculateCurrentScore()));

        applyBonusNinthFrame(frame);
        applyBonusEighthFrame(frame);

        return new ArrayList<>(this.bowlingGameResults);
    }

    public List<BowlingGameResult> finalFrameBowlSecond(int numberOfHitPin) {
        Frame frame = player.bowlCurrentFrameRefactor(numberOfHitPin);

        this.bowlingGameResults.set(lastIndexOfBowlingGameResults(), new BowlingGameResult(
                frame.calculateCurrentResults(),
                frame.calculateCurrentScore()
        ));

        BowlingGameResult previousBowlingGameResult =
                this.bowlingGameResults.get(lastIndexOfBowlingGameResults() - 1);
        BowlingGameResult bonusApplied = previousBowlingGameResult.applyBonus(frame.calculateSpecialStrikeScore());
        this.bowlingGameResults.set(lastIndexOfBowlingGameResults() - 1, bonusApplied);

        return new ArrayList<>(this.bowlingGameResults);
    }

    public List<BowlingGameResult> finalFrameBowlLast(int numberOfHitPin) {
        Frame frame = player.bowlCurrentFrameRefactor(numberOfHitPin);

        this.bowlingGameResults.set(lastIndexOfBowlingGameResults(), new BowlingGameResult(
                frame.calculateCurrentResults(),
                frame.calculateCurrentScore()
        ));

        return new ArrayList<>(this.bowlingGameResults);
    }

    public boolean isCurrentFrameCompleted() {
        return this.player.isCurrentFrameCompleted();
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public List<BowlingGameResult> getBowlingGameResults() {
        return new ArrayList<>(this.bowlingGameResults);
    }

    private void applyBonusEighthFrame(Frame frame) {
        BowlingGameResult eightBowlingGameResult = this.bowlingGameResults.get(lastIndexOfBowlingGameResults() - 2);
        this.bowlingGameResults.set(lastIndexOfBowlingGameResults() - 2,
                eightBowlingGameResult.applyBonus(frame.calculateSpecialStrikeScore()));
    }

    private void applyBonusNinthFrame(Frame frame) {
        BowlingGameResult ninthBowlingGameResult = this.bowlingGameResults.get(lastIndexOfBowlingGameResults() - 1);
        this.bowlingGameResults.set(lastIndexOfBowlingGameResults() - 1,
                ninthBowlingGameResult.applyBonus(frame.calculatePreviousScore()));
    }

    private void applyBonusToPreviousFrame(Frame frame) {
        if (lastIndexOfBowlingGameResults() != 0) {
            BowlingGameResult previousBowlingGameResult =
                    this.bowlingGameResults.get(lastIndexOfBowlingGameResults() - 1);
            BowlingGameResult bonusApplied = previousBowlingGameResult.applyBonus(frame.calculatePreviousScore());
            this.bowlingGameResults.set(lastIndexOfBowlingGameResults() - 1, bonusApplied);
        }
    }

    private void applySpecialStrikeBonus(Frame frame) {
        if (lastIndexOfBowlingGameResults() > 1) {
            BowlingGameResult twoFrameAgoBowlingGameResult =
                    this.bowlingGameResults.get(lastIndexOfBowlingGameResults() - 2);
            BowlingGameResult bonusApplied =
                    twoFrameAgoBowlingGameResult.applyBonus(frame.calculateSpecialStrikeScore());
            this.bowlingGameResults.set(lastIndexOfBowlingGameResults() - 2, bonusApplied);
        }
    }

    private int lastIndexOfBowlingGameResults() {
        return this.bowlingGameResults.size() - 1;
    }
}
