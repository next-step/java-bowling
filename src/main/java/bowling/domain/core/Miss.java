package bowling.domain.core;

import bowling.domain.frame.TerminateFrame;

final class Miss extends AbstractTwoFallenPinsRolledResult {

    public Miss(ImmutableTwoFallenPins towFallenPins) {
        super(towFallenPins);
    }

    @Override
    public int tryCountByTerminateFrame() {
        return TerminateFrame.MAX_TRY_COUNT_SIZE;
    }

    @Override
    public String description() {
        return String.format("%s|%s", gutterOrFallenPinValue(0), gutterOrFallenPinValue(1));
    }
}
