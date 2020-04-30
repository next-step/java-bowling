package bowling.domain.shot.type;

import bowling.domain.shot.Pins;

class SpareChecker implements TypeChecker {

    @Override
    public boolean availableFirstShots(Pins firstShot) {
        return false;
    }

    @Override
    public boolean availableSecondShots(Pins firstShot, Pins secondShot) {
        return firstShot.getLeftScore().equals(secondShot);
    }

}
