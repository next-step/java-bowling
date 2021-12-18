package bowling.domain.bowl;

import bowling.domain.pin.Pin;

public abstract class FinishedBowl implements Bowl {

    @Override
    public Bowl pitch(Pin pin) {
        throw new CanNotPitchException();
    }

    @Override
    public boolean canPitch() {
        return false;
    }
}
