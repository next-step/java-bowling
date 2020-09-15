package bowling.domain.core;

import static bowling.domain.core.FallenPins.zero;
import static bowling.domain.core.ImmutableTwoFallenPins.of;

final class  IncompleteState extends AbstractTwoFallenPinsRolledResult {
    IncompleteState(FallenPins firstFallenFallenPins) {
        super(of(firstFallenFallenPins, zero()));
    }

    @Override
    public boolean isCompleteState(){
        return false;
    }

    @Override
    public String description() {
        return String.format("%s|?", gutterOrFallenPinValue(0));
    }
}
