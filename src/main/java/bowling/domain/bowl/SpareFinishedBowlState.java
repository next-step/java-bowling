package bowling.domain.bowl;

public class SpareFinishedBowlState extends FinishedBowlState {
    SpareFinishedBowlState(BowlState state) {
        super(state);
    }

    @Override
    void updateState(Bowl bowl) {
        boolean isLast = isLast();
        BowlState nextState = isLast
                ? new SpareBonusBowlState(this)
                : new FirstBowlState(this);
        bowl.setState(nextState);
    }
}
