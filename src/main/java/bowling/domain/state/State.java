package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.CannotCalculateScoreException;

public interface State {
    String CANNOT_ADD_SCORE_STATUS = "점수를 더할 수 없는 상태";
    String CANNOT_CALCULATE_SCORE_STATUS = "점수를 계산 할 수 없는 상태";

    static State ofSpare(int firstHitCount) {
        return new Spare(firstHitCount);
    }

    static State ofStrike() {
        return new Strike();
    }

    static State ofMiss(int firstHitCount, int secondHitCount) {
        return new Miss(firstHitCount, secondHitCount);
    }

    static State ofReady() {
        return new Ready();
    }

    static State ofFirstBowl(int hitCount) {
        return new FirstBowl(hitCount);
    }

    State bowl(int hitCount);

    String output();

    default Score addBonus(Score previousScore) {
        throw new CannotCalculateScoreException(CANNOT_ADD_SCORE_STATUS);
    }

    default Score score() {
        throw new CannotCalculateScoreException(CANNOT_CALCULATE_SCORE_STATUS);
    }

    default boolean isDone() {
        return isStrike()
            || isSpare()
            || isMiss();
    }

    default boolean canCalculateScore() {
        return isDone();
    }

    default boolean isStrike() {
        return this instanceof Strike;
    }

    default boolean isSpare() {
        return this instanceof Spare;
    }

    default boolean isMiss() {
        return this instanceof Miss;
    }

}
