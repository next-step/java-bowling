package bowling.domain.shot.type;

import bowling.domain.shot.Score;

class SpareChecker implements TypeChecker {

    @Override
    public boolean availableFirstShots(Score firstShot) {
        return false;
    }

    @Override
    public boolean availableSecondShots(Score firstShot, Score secondShot) {
        return firstShot.getLeftScore().equals(secondShot);
    }

}
