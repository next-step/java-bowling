package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class FirstBowl implements State {
    private Pins firstPins;

    FirstBowl(int hitCount) {
        this.firstPins = new Pins(hitCount);
    }

    public int firstScore() {
        return firstPins.hitCount();
    }

    @Override
    public Score addBonus(Score score) {
        score = score.add(firstScore());
        if (score.isAddedAllBonus()) {
            return score;
        }

        throw new IllegalStateException("두번째 던질 때 까지 보너스 점수 계산 불가");
    }

    @Override
    public State bowl(int secondHitCount) {
        if (firstPins.isHitAllAfter(secondHitCount)) {
            return State.ofSpare(firstPins.hitCount());
        }

        return State.ofMiss(firstPins.hitCount(), secondHitCount);
    }

}
