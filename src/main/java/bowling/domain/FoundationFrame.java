package bowling.domain;

import java.util.Optional;

import bowling.domain.core.RolledResult;

final class FoundationFrame implements Frame {
    private RolledResult rolledResult;

    @Override
    public void updateRolledResult(RolledResult rolledResult) {
        this.rolledResult = rolledResult;
    }

    @Override
    public String toRolledResult() {
        return Optional.ofNullable(rolledResult)
                       .map(RolledResult::description)
                       .orElse("");
    }

    @Override
    public int increaseNextStepFrame() {
        return Optional.ofNullable(rolledResult)
                       .filter(RolledResult::isCompleteState)
                       .map(r -> 1)
                       .orElse(0);
    }
}
