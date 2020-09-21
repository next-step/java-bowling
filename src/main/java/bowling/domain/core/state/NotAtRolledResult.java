package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

import static bowling.domain.core.state.ImmutableTwoFallenPins.gutterTwoFallenPins;
import static bowling.domain.core.state.Strike.strike;

public final class NotAtRolledResult implements RolledResult {
    private static final RolledResult notAtRolledResult = new NotAtRolledResult();

    public static RolledResult notAtRolledResult() { return notAtRolledResult; }

    private NotAtRolledResult() {}

    @Override
    public boolean isCompleteState() {
        return false;
    }

    @Override
    public int tryCountByTerminateFrame() {
        return 0;
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        return 0;
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return gutterTwoFallenPins();
    }

    @Override
    public RolledResult nextRolledResult(int fallenPinsValue) {
        final FallenPins fallenPins = FallenPins.of(fallenPinsValue);
        if (isStrike(fallenPins)){
            return strike;
        }
        return new Miss(fallenPins);
    }

    private boolean isStrike(FallenPins fallenPins) {
        return FallenPins.ten().equals(fallenPins);
    }
}
