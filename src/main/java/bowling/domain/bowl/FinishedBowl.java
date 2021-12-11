package bowling.domain.bowl;

public abstract class FinishedBowl implements Bowl {

    @Override
    public boolean canPitch() {
        return false;
    }
}
