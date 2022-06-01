package bowling.domain.state;

import static bowling.domain.ScoreSymbols.SCORE_SEPARATOR;

import bowling.domain.Score;

public class Miss implements State {
    private final int firstHitCount;
    private final int secondHitCount;

    public Miss(int firstHitCount, int secondHitCount) {
        this.firstHitCount = firstHitCount;
        this.secondHitCount = secondHitCount;
    }

    @Override
    public Score addBonus(Score score) {
        score = score.add(firstHitCount);

        if (score.isAddedAllBonus()) {
            return score;
        }
        return score.add(secondHitCount);
    }

    @Override
    public Score score() {
        return new Score(firstHitCount + secondHitCount, 0);
    }

    @Override
    public State bowl(int countOfPin) {
        throw new IllegalStateException("두번 다 던진 이 후에는 볼을 더 던질 수 없음");
    }

    @Override
    public String output() {
        return firstHitCount + SCORE_SEPARATOR + secondHitCount;
    }
}
