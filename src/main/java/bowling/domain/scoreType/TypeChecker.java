package bowling.domain.scoreType;

import bowling.domain.Score;

interface TypeChecker {
    boolean availableFirstShots(Score firstShot);

    boolean availableSecondShots(Score firstShot, Score secondShot);
}
