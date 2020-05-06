package bowling.domain.shot.type;

import bowling.domain.shot.Pins;

public class GutterChecker implements TypeChecker {
    private final boolean isFirst;

    public GutterChecker(boolean isFirst) {
        this.isFirst = isFirst;
    }

    @Override
    public boolean availableFirstShots(Pins firstShot) {
        if (!isFirst) {
            return false;
        }
        return firstShot.isMin();
    }

    @Override
    public boolean availableSecondShots(Pins firstShot, Pins secondShot) {
        if (isFirst) {
            return false;
        }
        return secondShot.isMin();
    }
}
