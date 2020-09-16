package bowling.domain.core;

import bowling.domain.frame.TerminateFrame;

final class Gutter extends AbstractTwoFallenPinsRolledResult {
    static final RolledResult gutter = new Gutter();

    public Gutter() {
        super(ImmutableTwoFallenPins.gutter());
    }

    @Override
    public int tryCountByTerminateFrame() {
        return TerminateFrame.MAX_TRY_COUNT_SIZE;
    }

    @Override
    public String description() {
        return "-";
    }
}
