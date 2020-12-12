package bowling.domain.bowl;

public class SecondBowlState extends BowlState {

    SecondBowlState(BowlState state) {
        super(state);
    }

    @Override
    public boolean isPlayable(Bowl bowl) {
        return true;
    }

    @Override
    void updateState(Bowl bowl) {
        boolean isLast = isLast();
        BowlState nextState = isLast && isSpare()
                ? new SpareBonusBowlState(this)
                : isLast
                ? new GameOverBowlState(this)
                : new FirstBowlState(this);
        bowl.setState(nextState);
    }
}
