package bowling.domain.core.state;

import bowling.domain.core.RolledResult;
import bowling.domain.frame.TerminateFrame;

import static bowling.domain.core.state.ImmutableTwoFallenPins.gutterTwoFallenPins;

final class Gutter implements RolledResult {
    static final RolledResult gutter = new Gutter();

    @Override
    public int tryCountByTerminateFrame() {
        return TerminateFrame.MAX_TRY_COUNT_SIZE;
    }

    @Override
    public String description() {
        return "-";
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return gutterTwoFallenPins();
    }
}
