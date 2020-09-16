package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

import static bowling.domain.core.FallenPins.zero;
import static bowling.domain.core.state.ImmutableTwoFallenPins.of;

final class SpareAfterBonusBowl extends AbstractTwoFallenPinsRolledResult {
    SpareAfterBonusBowl(RolledResult rolledResult) {
        super(of(firstFallenPins(rolledResult), zero()));
    }

    private static FallenPins firstFallenPins(RolledResult rolledResult) {
        return rolledResult.twoFallenPins()
                           .firstFallenPins();
    }

    @Override
    public String description() {
        return String.format("%s",gutterOrFallenPinValue(0));
    }
}
