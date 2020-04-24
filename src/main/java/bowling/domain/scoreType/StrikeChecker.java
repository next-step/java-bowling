package bowling.domain.scoreType;

import bowling.domain.Score;

class StrikeChecker implements TypeChecker {

    @Override
    public boolean availableFirstShots(Score firstShot) {
        return firstShot.isMax();
    }

    @Override
    public boolean availableSecondShots(Score firstShot, Score secondShot) {
        return false;
    }

}
