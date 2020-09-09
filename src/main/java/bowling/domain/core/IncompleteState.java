package bowling.domain.core;

import static bowling.domain.core.FallenPins.zero;

final class  IncompleteState extends AbstractTwoFallenPinsRolledResult {

    IncompleteState(FallenPins firstFallenFallenPins) {
        super(new ImmutableTwoFallenPins(firstFallenFallenPins, zero()));
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
