package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

import static bowling.domain.core.FallenPins.zero;
import static bowling.domain.core.state.ImmutableTwoFallenPins.of;

final class SpareAfterBonusBowl implements RolledResult{

    private final FallenPins firstFallenFallenPins;

    SpareAfterBonusBowl(RolledResult rolledResult) {
        this.firstFallenFallenPins = firstFallenPins(rolledResult);
    }

    private static FallenPins firstFallenPins(RolledResult rolledResult) {
        return rolledResult.twoFallenPins()
                           .firstFallenPins();
    }
    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return of(firstFallenFallenPins, zero());
    }

    @Override
    public String description() {
        return String.format("%s",gutterOrFallenPinValue(0));
    }
}
