package bowling.domain.state;

import static bowling.domain.state.Spare.SCORE_SEPARATOR;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.exception.CannotCalculateScoreException;

public class FirstBowl implements State {
    private Pins firstPins;

    FirstBowl(int hitCount) {
        this.firstPins = new Pins(hitCount);
    }

    public int firstScore() {
        if (this.firstPins != null) {
            return firstPins.hitCount();
        }

        throw new CannotCalculateScoreException("아직 점수가 없음");
    }

    @Override
    public Score addBonus(Score score) {
        score = score.add(firstScore());
        if (score.isAddedAllBonus()) {
            return score;
        }

        throw new CannotCalculateScoreException("두번째 던질 때 까지 보너스 점수 계산 불가");
    }

    @Override
    public State bowl(int secondHitCount) {
        if (firstPins.isHitAllAfter(secondHitCount)) {
            return State.ofSpare(firstPins.hitCount());
        }

        return State.ofMiss(firstPins.hitCount(), secondHitCount);
    }

    @Override
    public String output() {
        return firstScore() + SCORE_SEPARATOR + " ";
    }

}
