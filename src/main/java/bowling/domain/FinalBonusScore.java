package bowling.domain;

import java.util.Objects;

public class FinalBonusScore {
    private final int firstThrowScore;
    private final int secondThrowScore;
    private final boolean strikeBonus;

    FinalBonusScore(int firstThrowScore, int secondThrowScore, boolean strikeBonus) {
        this.firstThrowScore = firstThrowScore;
        this.secondThrowScore = secondThrowScore;
        this.strikeBonus = strikeBonus;
    }

    public static FinalBonusScore of(NumberOfHitPin firstHitPin, NumberOfHitPin secondHitPin) {
        return new FinalBonusScore(
                (firstHitPin == null) ? 0 : firstHitPin.parseToInt(),
                (secondHitPin == null) ? 0 : secondHitPin.parseToInt(),
                (firstHitPin != null) && (secondHitPin != null)
        );
    }

    public int getFirstThrowScore() {
        return this.firstThrowScore;
    }

    public int getStrikeBonus() {
        return this.firstThrowScore + this.secondThrowScore;
    }

    public boolean isStrikeBonus() {
        return this.strikeBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalBonusScore that = (FinalBonusScore) o;
        return firstThrowScore == that.firstThrowScore &&
                secondThrowScore == that.secondThrowScore &&
                strikeBonus == that.strikeBonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstThrowScore, secondThrowScore, strikeBonus);
    }
}
