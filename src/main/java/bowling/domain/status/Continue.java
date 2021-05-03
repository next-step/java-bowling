package bowling.domain.status;

public abstract class Continue implements Status {
    private static final int NO_BONUS_PITCH = 0;

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public int bonusPitchCount() {
        return NO_BONUS_PITCH;
    }
}
