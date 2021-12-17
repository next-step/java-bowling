package bowling.domain.bowl;

import bowling.domain.pin.Pin;

import java.util.List;

import static java.util.Collections.emptyList;

public abstract class ProceedingBowl implements Bowl {

    @Override
    public boolean canPitch() {
        return true;
    }

    @Override
    public List<Pin> pins() {
        return emptyList();
    }
}
