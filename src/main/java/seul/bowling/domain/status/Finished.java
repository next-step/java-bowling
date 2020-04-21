package seul.bowling.domain.status;

public abstract class Finished implements Status {
    @Override
    public Status addPins(int clearPin) {
        return this;
    }

    @Override
    public void addBonusScore(int bonusScore) {
    }

    @Override
    public boolean end() {
        return true;
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public boolean endCalculateScore() {
        return true;
    }
}
