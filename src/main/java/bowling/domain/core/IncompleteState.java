package bowling.domain.core;

import static bowling.domain.core.Pins.zero;

final class  IncompleteState extends AbstractTowFallenPinsRolledResult {

    IncompleteState(Pins firstFallenPins) {
        super(new ImmutableTowFallenPins(firstFallenPins, zero()));
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
