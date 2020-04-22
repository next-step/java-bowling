package seul.bowling.domain.status;

public abstract class Running implements Status {
    private static final int ZERO = 0;

    @Override
    public void addCumulativeScore(int score) {
    }

    @Override
    public int getToTalScore() {
        return ZERO;
    }

    @Override
    public void addBonusScore(int bonusScore) {
    }

    @Override
    public boolean end() {
        return false;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean endCalculateScore() {
        return false;
    }
}
