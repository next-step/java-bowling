package bowling.domain.shot.type;

import bowling.domain.shot.Score;

interface TypeChecker {
    boolean availableFirstShots(Score firstShot);

    boolean availableSecondShots(Score firstShot, Score secondShot);
}
