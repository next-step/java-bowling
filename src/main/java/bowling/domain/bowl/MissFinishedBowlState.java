package bowling.domain.bowl;

public class MissFinishedBowlState extends FinishedBowlState {
    MissFinishedBowlState(BowlState state) {
        super(state);
    }

    @Override
    void updateState(Bowl bowl) {
        boolean isLast = isLast();
        BowlState nextState = isLast
                ? new GameOverBowlState(this)
                : new FirstBowlState(this);
        bowl.setState(nextState);
    }
}
