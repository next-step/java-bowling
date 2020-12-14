package bowling.domain.bowl;

class MissFinishedBowlState extends FinishedBowlState {
    MissFinishedBowlState(BowlState state) {
        super(state);
    }

    @Override
    void updateState(Bowl bowl) {
        BowlState nextState = isLast()
                ? new GameOverBowlState(this)
                : new FirstBowlState(this);
        bowl.setState(nextState);
    }
}
