package bowling.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FrameRounds {
    private static final int MAX_ROUND_SIZE = 3;

    @Getter
    private ScoreStatus scoreStatus;
    @Getter
    private List<FrameRound> frameRounds;

    public FrameRounds() {
        this.scoreStatus = new ScoreStatus();
        this.frameRounds = new ArrayList<>();
    }

    public void play(int clearCount, boolean isLastFrame) {
        int roundIndex = this.frameRounds.size();
        frameRounds.add(new FrameRound(roundIndex, clearCount));

        scoreStatus.update(roundIndex, clearCount, totalClearPinCount(), isLastFrame);
    }

    public boolean isEnd(boolean isLastFrame) {
        if (isLastFrame
                && (this.scoreStatus.isStrike() || this.scoreStatus.isSpare())
                && !isMaxRoundSize(this.frameRounds.size())) {
            return false;
        }

        return !this.scoreStatus.isNone();
    }

    public boolean availableBonus() {
        return scoreStatus.availableBonus();
    }

    public boolean endCalculate() {
        return scoreStatus.endCalculate();
    }

    public void addScore(int score) {
        scoreStatus.addScore(score);
    }

    public int getTotalScore() {
        return scoreStatus.getTotalScore();
    }

    public void updateBonus(int bonusScore) {
        scoreStatus.updateBonus(bonusScore);
    }

    private int totalClearPinCount() {
        return frameRounds.stream()
                .mapToInt(FrameRound::getClearPinCount)
                .sum();
    }

    private boolean isMaxRoundSize(int size) {
        return size == MAX_ROUND_SIZE;
    }
}
