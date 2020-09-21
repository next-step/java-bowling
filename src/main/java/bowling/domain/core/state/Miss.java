package bowling.domain.core.state;

import bowling.domain.core.RolledResult;
import bowling.domain.frame.TerminateFrame;

final class Miss implements RolledResult {
    private final ImmutableTwoFallenPins twoFallenPins;

    Miss(ImmutableTwoFallenPins twoFallenPins) {
        this.twoFallenPins = twoFallenPins;
    }

    @Override
    public int tryCountByTerminateFrame() {
        return TerminateFrame.MAX_TRY_COUNT_SIZE;
    }

    @Override
    public String description() {
        return String.format("%s|%s", gutterOrFallenPinValue(0), gutterOrFallenPinValue(1));
    }

    @Override
    public int getNextRolledResultMergeScore(RolledResult nextRolledResult) {
        return twoFallenPins.totalScore();
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return twoFallenPins;
    }
}
