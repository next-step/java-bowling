package bowling.domain.scoreType;

import bowling.domain.Score;

public class GutterChecker implements TypeChecker {
    private final boolean isFirst;

    public GutterChecker(boolean isFirst) {
        this.isFirst = isFirst;
    }

    @Override
    public boolean availableFirstShots(Score firstShot) {
        if (!isFirst) {
            return false;
        }
        return firstShot.isMin();
    }

    @Override
    public boolean availableSecondShots(Score firstShot, Score secondShot) {
        if (isFirst) {
            return false;
        }
        return secondShot.isMin();
    }
}
