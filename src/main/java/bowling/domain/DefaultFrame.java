package bowling.domain;

public class DefaultFrame extends Frame {

    private static final int TOTAL_CHANCE = 2;

    @Override
    protected int TotalChance() {
        return TOTAL_CHANCE;
    }

    public void minusChance() {
        if (this.scores.first().isStrike()) {
            this.chance.minusTwo();
            return;
        }
        this.chance.minusOne();
    }

    @Override
    protected void validateScore(Frame frame) {
        if (this.scores.sum() > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isNotEndScoreAggregation() {
        if (this.scores.isEmpty()) {
            return true;
        }
        if (this.scores.size() == 1 && !this.scores.first().isStrike()) {
            return true;
        }
        if (this.scores.first().isStrike()
                || Scores.sumScores(this.scores.first(), this.scores.second()) == SCORE_STRIKE) {
            return isNotEndScoreAggregationStrikeOrSpare();
        }
        return false;
    }

    private boolean isNotEndScoreAggregationStrikeOrSpare() {
        return this.scores.size() != TOTAL_CHANCE + 1;
    }
}
