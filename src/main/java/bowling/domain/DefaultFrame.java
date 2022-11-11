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
        }
        this.chance.minusOne();
    }

    @Override
    protected void validateScore(Frame frame) {
        if (this.scores.sum() > SCORE_STRIKE) {
            throw new IllegalArgumentException();
        }
    }
}
