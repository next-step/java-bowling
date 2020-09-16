package bowling.domain.core.state;

import bowling.domain.core.RolledResult;
import bowling.domain.frame.TerminateFrame;

import static bowling.domain.core.state.ImmutableTwoFallenPins.gutterTwoFallenPins;

final class Gutter extends AbstractTwoFallenPinsRolledResult {
    static final RolledResult gutter = new Gutter();

    private Gutter() {
        super(gutterTwoFallenPins());
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
