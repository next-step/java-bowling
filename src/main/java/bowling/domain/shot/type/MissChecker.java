package bowling.domain.shot.type;


import bowling.domain.shot.Pins;

public class MissChecker implements TypeChecker {
    private final boolean isFirst;

    public MissChecker(boolean isFirst) {
        this.isFirst = isFirst;
    }

    @Override
    public boolean availableFirstShots(Pins firstShot) {
        if (!isFirst) {
            return false;
        }
        return firstShot.range(1, 10);
    }

    @Override
    public boolean availableSecondShots(Pins firstShot, Pins secondShot) {
        if (isFirst) {
            return false;
        }
        return secondShot.range(1, firstShot.getLeftScore().score());
    }
}
