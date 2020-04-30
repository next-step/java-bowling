package bowling.domain.shot.type;

import bowling.domain.shot.Pins;

class StrikeChecker implements TypeChecker {

    @Override
    public boolean availableFirstShots(Pins firstShot) {
        return firstShot.isMax();
    }

    @Override
    public boolean availableSecondShots(Pins firstShot, Pins secondShot) {
        return false;
    }

}
