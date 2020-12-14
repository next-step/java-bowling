package bowling.domain.bowl;

class StrikeFinishedBowlState extends FinishedBowlState {
    StrikeFinishedBowlState(BowlState state) {
        super(state);
    }

    @Override
    void updateState(Bowl bowl) {
        BowlState nextState = isLast()
                ? new StrikeBonusBowlState(this)
                : new FirstBowlState(this);
        bowl.setState(nextState);
    }
}
