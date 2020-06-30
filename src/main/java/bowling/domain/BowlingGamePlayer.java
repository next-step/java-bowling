package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class BowlingGamePlayer {
    private final Player player;
    private BowlingGameResults bowlingGameResults;

    public BowlingGamePlayer(Player player, List<BowlingGameResult> bowlingGameResults) {
        this.player = player;
        this.bowlingGameResults = BowlingGameResults.of(bowlingGameResults);
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

    public BowlingGameResults bowlFirst(int numberOfHitPin) {
        Frame frame = player.bowlFirstRefactor(numberOfHitPin);

        this.bowlingGameResults = this.bowlingGameResults.add(
                new BowlingGameResult(frame.calculateCurrentResults(), frame.calculateCurrentScore())
        );

        return this.bowlingGameResults;
    }

    public BowlingGameResults bowlCurrentFrame(int numberOfHitPin) {
        Frame frame = player.bowlCurrentFrameRefactor(numberOfHitPin);

        this.bowlingGameResults = this.bowlingGameResults.updateCurrentFrame(
                new BowlingGameResult(frame.calculateCurrentResults(), frame.calculateCurrentScore())
        );

        applyBonusToPreviousFrame(frame);

        return this.bowlingGameResults;
    }

    public BowlingGameResults toNextFrame(int numberOfHitPin) {
        Frame frame = player.toNextFrameRefactor(numberOfHitPin);

        BowlingGameResult bowlingGameResult = new BowlingGameResult(
                frame.calculateCurrentResults(),
                frame.calculateCurrentScore()
        );

        this.bowlingGameResults = this.bowlingGameResults.add(bowlingGameResult);
        applyBonusToPreviousFrame(frame);
        applySpecialStrikeBonus(frame);

        return this.bowlingGameResults;
    }

    public BowlingGameResults finalFrameBowlFirst(int numberOfHitPin) {
        Frame frame = player.toNextFrameRefactor(numberOfHitPin);

        this.bowlingGameResults = this.bowlingGameResults.add(new BowlingGameResult(
                frame.calculateCurrentResults(),
                frame.calculateCurrentScore()));

        applyBonusNinthFrame(frame);
        applyBonusEighthFrame(frame);

        return this.bowlingGameResults;
    }

    public BowlingGameResults finalFrameBowlSecond(int numberOfHitPin) {
        Frame frame = player.bowlCurrentFrameRefactor(numberOfHitPin);

        this.bowlingGameResults = this.bowlingGameResults.updateCurrentFrame(
                new BowlingGameResult(frame.calculateCurrentResults(), frame.calculateCurrentScore())
        );

        this.bowlingGameResults = this.bowlingGameResults.applyBonusToPreviousFrame(
                frame.calculateSpecialStrikeScore());

        return this.bowlingGameResults;
    }

    public BowlingGameResults finalFrameBowlLast(int numberOfHitPin) {
        Frame frame = player.bowlCurrentFrameRefactor(numberOfHitPin);

        this.bowlingGameResults = this.bowlingGameResults.updateCurrentFrame(new BowlingGameResult(
                frame.calculateCurrentResults(),
                frame.calculateCurrentScore()
        ));

        return this.bowlingGameResults;
    }

    public boolean isCurrentFrameCompleted() {
        return this.player.isCurrentFrameCompleted();
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public List<BowlingGameResult> getBowlingGameResults() {
        return this.bowlingGameResults.getValues();
    }

    private void applyBonusEighthFrame(Frame frame) {
        this.bowlingGameResults =
                this.bowlingGameResults.applyBonusToTwoFramesAgo(frame.calculateSpecialStrikeScore());
    }

    private void applyBonusNinthFrame(Frame frame) {
        applyBonusToPreviousFrame(frame);
    }

    private void applyBonusToPreviousFrame(Frame frame) {
        if (lastIndexOfBowlingGameResults() != 0) {
            this.bowlingGameResults =
                    this.bowlingGameResults.applyBonusToPreviousFrame(frame.calculatePreviousScore());
        }
    }

    private void applySpecialStrikeBonus(Frame frame) {
        if (lastIndexOfBowlingGameResults() > 1) {
            this.bowlingGameResults =
                    this.bowlingGameResults.applyBonusToTwoFramesAgo(frame.calculateSpecialStrikeScore());
        }
    }

    private int lastIndexOfBowlingGameResults() {
        return this.bowlingGameResults.size() - 1;
    }
}
