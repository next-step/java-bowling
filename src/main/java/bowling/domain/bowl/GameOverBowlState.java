package bowling.domain.bowl;

public class GameOverBowlState extends BowlState {
    GameOverBowlState(BowlState state) {
        super(state);
    }

    @Override
    public boolean isPlayable() {
        return false;
    }

    @Override
    void updateState(Bowl bowl) {}
}
