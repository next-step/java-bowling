package bowling.domain.state;

import static bowling.domain.ScoreSymbols.SCORE_SEPARATOR;
import static bowling.domain.ScoreSymbols.SPARE_SYMBOL;

import bowling.domain.Score;

public class Spare implements State {
    private static final String CANNOT_THROW_AFTER_SPARE = "스페어 이 후에는 볼을 더 던질 수 없음";

    private final int firstHitCount;

    public Spare(int firstHitCount) {
        this.firstHitCount = firstHitCount;
    }

    @Override
    public Score addBonus(Score score) {
        score = score.add(firstHitCount);
        if (score.isAddedAllBonus()) {
            return score;
        }

        return score.add(10 - firstHitCount);
    }

    @Override
    public Score score() {
        return Score.ofSpare();
    }

    @Override
    public State bowl(int countOfPin) {
        throw new IllegalStateException(CANNOT_THROW_AFTER_SPARE);
    }

    @Override
    public String output() {
        return firstHitCount + SCORE_SEPARATOR + SPARE_SYMBOL;
    }
}
