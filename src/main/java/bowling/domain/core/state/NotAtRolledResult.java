package bowling.domain.core.state;

import bowling.domain.core.RolledResult;

import static bowling.domain.core.state.ImmutableTwoFallenPins.gutterTwoFallenPins;

public final class NotAtRolledResult implements RolledResult {
    private static final RolledResult notAtRolledResult = new NotAtRolledResult();

    public static RolledResult notAtRolledResult() { return notAtRolledResult; }

    private NotAtRolledResult() {}

    @Override
    public boolean isCompleteState() {
        return false;
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        return 0;
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return gutterTwoFallenPins();
    }
}
