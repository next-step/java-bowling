package bowling.domain.state;

import static bowling.domain.ScoreSymbols.SCORE_SEPARATOR;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.exception.CannotCalculateScoreException;

public class FirstBowl implements State {
    private static final String NO_SCORE_YET = "아직 점수가 없음";
    private static final String CANNOT_CALCULATE_TILL_BONUS_END = "두번째 던질 때 까지 보너스 점수 계산 불가";

    private Pins firstPins;

    FirstBowl(int hitCount) {
        this.firstPins = new Pins(hitCount);
    }

    public int firstScore() {
        if (this.firstPins != null) {
            return firstPins.hitCount();
        }

        throw new CannotCalculateScoreException(NO_SCORE_YET);
    }

    @Override
    public Score addBonus(Score score) {
        score = score.add(firstScore());
        if (score.isAddedAllBonus()) {
            return score;
        }

        throw new CannotCalculateScoreException(CANNOT_CALCULATE_TILL_BONUS_END);
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
