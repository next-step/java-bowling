package bowling.domain.bowl;

class SpareFinishedBowlState extends FinishedBowlState {
    SpareFinishedBowlState(BowlState state) {
        super(state);
    }

    @Override
    void updateState(Bowl bowl) {
        BowlState nextState = isLast()
                ? new SpareBonusBowlState(this)
                : new FirstBowlState(this);
        bowl.setState(nextState);
    }
}
