package bowling.domain.core;

import bowling.domain.TerminateFrame;

final class Miss extends AbstractSecondRolledResult {

    Miss(Pins first, Pins second) {
        super(first, second);
    }

    @Override
    public int tryCountByTerminateFrame() {
        return TerminateFrame.MAX_TRY_COUNT_SIZE;
    }

    @Override
    public String description() {
        return String.format("%s|%s", gutterOrFallenPinCount(0), gutterOrFallenPinCount(1));
    }
}
