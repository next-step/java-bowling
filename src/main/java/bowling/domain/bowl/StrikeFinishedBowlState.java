package bowling.domain.bowl;

public class StrikeFinishedBowlState extends FinishedBowlState {
    StrikeFinishedBowlState(BowlState state) {
        super(state);
    }

    @Override
    void updateState(Bowl bowl) {
        boolean isLast = isLast();
        BowlState nextState = isLast
                ? new StrikeBonusBowlState(this)
                : new FirstBowlState(this);
        bowl.setState(nextState);
    }
}
