package bowling.domain.scoreType;


import bowling.domain.Score;

public class MissChecker implements TypeChecker {
    private final boolean isFirst;

    public MissChecker(boolean isFirst) {
        this.isFirst = isFirst;
    }

    @Override
    public boolean availableFirstShots(Score firstShot) {
        if (!isFirst) {
            return false;
        }
        return firstShot.range(1,10);
    }

    @Override
    public boolean availableSecondShots(Score firstShot, Score secondShot) {
        if (isFirst) {
            return false;
        }
        return secondShot.range(1, firstShot.getLeftScore().score());
    }
}
