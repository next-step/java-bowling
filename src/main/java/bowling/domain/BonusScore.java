package bowling.domain;

import bowling.domain.exceptions.ExceedBonusLimitException;
import bowling.domain.exceptions.InvalidBonusScoreArgumentException;

import java.util.Objects;

public class BonusScore {
    private static final int MAXIMUM_BONUS_SCORE = 10;

    private final int firstThrowScore;
    private final int secondThrowScore;
    private final boolean strikeBonus;

    public BonusScore(int firstThrowScore, int secondThrowScore, boolean strikeBonus) {
        if (firstThrowScore + secondThrowScore > MAXIMUM_BONUS_SCORE) {
            throw new ExceedBonusLimitException("보너스 점수는 최대 10점까지만 생성 가능합니다.");
        }
        this.firstThrowScore = firstThrowScore;
        this.secondThrowScore = secondThrowScore;
        this.strikeBonus = strikeBonus;
    }

    public static BonusScore of(NumberOfHitPin firstHitPin, NumberOfHitPin secondHitPin) {
        if (firstHitPin == null && secondHitPin != null) {
            throw new InvalidBonusScoreArgumentException("첫 투구 없이 두번째 투구만 진행한 경우는 존재하지 않습니다.");
        }
        return new BonusScore(
                (firstHitPin == null) ? 0 : firstHitPin.parseToInt(),
                (secondHitPin == null) ? 0 : secondHitPin.parseToInt(),
                judgeStrikeBonus(firstHitPin, secondHitPin)
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

    private static boolean judgeStrikeBonus(NumberOfHitPin firstHitPin, NumberOfHitPin secondHitPin) {
        return ((firstHitPin != null) && (secondHitPin != null)) ||
                (firstHitPin != null) && (firstHitPin.parseToInt() == 10);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusScore that = (BonusScore) o;
        return firstThrowScore == that.firstThrowScore &&
                secondThrowScore == that.secondThrowScore &&
                strikeBonus == that.strikeBonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstThrowScore, secondThrowScore, strikeBonus);
    }

    @Override
    public String toString() {
        return "BonusScore{" +
                "firstThrowScore=" + firstThrowScore +
                ", secondThrowScore=" + secondThrowScore +
                ", strikeBonus=" + strikeBonus +
                '}';
    }
}

