package bowling.domain;

import java.util.Optional;

public class TotalScore {

    private int totalScore;

    public TotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public static TotalScore ofInitialStrike() {
        return new TotalScore(10);
    }

    public int get() {
        return totalScore;
    }

    public void appendScoreOf(BonusShotCount bonusCount, Frame frame) {
        if (frame.isThrowFirst()) {
            totalScore += frame.firstScore();
            bonusCount.minus();
        }

        if (frame.isThrowSecond() && bonusCount.isRemained()) {
            totalScore += frame.secondScore();
            bonusCount.minus();
        }
    }

    public Optional<Integer> getAsOptional() {
        return Optional.of(totalScore);
    }
}
