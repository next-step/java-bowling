package bowling.domain;

import bowling.domain.core.RolledResult;

interface Frame {
    void updateRolledResult(RolledResult rolledResult);
    String toRolledResult();
    int increaseNextStepFrame();
}
