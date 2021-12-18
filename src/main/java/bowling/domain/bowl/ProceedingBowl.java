package bowling.domain.bowl;

public abstract class ProceedingBowl implements Bowl {

    @Override
    public boolean canPitch() {
        return true;
    }
}
