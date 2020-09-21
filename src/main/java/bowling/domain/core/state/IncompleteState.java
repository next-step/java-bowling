package bowling.domain.core.state;

import bowling.domain.core.FallenPins;
import bowling.domain.core.RolledResult;

import static bowling.domain.core.FallenPins.zero;
import static bowling.domain.core.state.ImmutableTwoFallenPins.of;

final class IncompleteState implements RolledResult {
    private final FallenPins firstFallenFallenPins;

    IncompleteState(FallenPins firstFallenFallenPins) {
        this.firstFallenFallenPins = firstFallenFallenPins;
    }

    @Override
    public boolean isCompleteState(){
        return false;
    }

    @Override
    public ImmutableTwoFallenPins twoFallenPins() {
        return of(firstFallenFallenPins, zero());
    }

    @Override
    public String description() {
        return String.format("%s|?", gutterOrFallenPinValue(0));
    }
}
