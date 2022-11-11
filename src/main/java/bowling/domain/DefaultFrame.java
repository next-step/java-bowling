package bowling.domain;

public class DefaultFrame extends Frame {

    private static final int TOTAL_CHANCE = 2;

    @Override
    protected int TotalChance() {
        return TOTAL_CHANCE;
    }

    @Override
    protected void addScore(Score score, Chance chance) {
        this.scores.add(score);
        this.chance = minusChance(score, chance);
    }

    private Chance minusChance(Score score, Chance chance) {
        if (score.isStrike()) {
            return chance.minusTwo();
        }
        return chance.minusOne();
    }

    @Override
    protected void validateScore(Frame frame) {
        if (this.scores.sum() > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }
}
