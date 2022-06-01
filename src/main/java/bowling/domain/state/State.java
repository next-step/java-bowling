package bowling.domain.state;

import bowling.domain.Score;

public interface State {
    String GAP = " ";

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
        throw new IllegalStateException("보너스 점수를 더할 수 없는 상태");
    }

    default Score score() {
        throw new IllegalStateException("점수 계산 불가한 상태");
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
