package bowling.domain.shot.type;

import bowling.domain.shot.Pins;

interface TypeChecker {
    boolean availableFirstShots(Pins firstShot);

    boolean availableSecondShots(Pins firstShot, Pins secondShot);
}
