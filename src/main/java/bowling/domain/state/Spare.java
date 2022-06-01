package bowling.domain.state;

import bowling.domain.Score;

public class Spare implements State {
    private static final String SPARE_SYMBOL = "/";
    private static final String SCORE_SEPARATOR = "|";

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
        throw new IllegalStateException("스페어 이 후에는 볼을 더 던질 수 없음");
    }

    @Override
    public String output() {
        return firstHitCount + SCORE_SEPARATOR + SPARE_SYMBOL;
    }
}
