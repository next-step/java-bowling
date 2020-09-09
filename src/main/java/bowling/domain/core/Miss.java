package bowling.domain.core;

import bowling.domain.TerminateFrame;

final class Miss extends AbstractTowFallenPinsRolledResult {

    public Miss(ImmutableTowFallenPins towFallenPins) {
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
