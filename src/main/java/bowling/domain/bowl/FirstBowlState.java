package bowling.domain.bowl;

public class FirstBowlState extends BowlState {
    FirstBowlState() {
        super();
    }

    FirstBowlState(BowlState state) {
        super(state);
    }

    @Override
    public boolean isPlayable(Bowl bowl) {
        return true;
    }

    @Override
    void updateState(Bowl bowl) {
        if (!isStrike()) {
            bowl.setState(new SecondBowlState(this));
            return;
        }
        if (isLast()) {
            bowl.setState(new StrikeBonusBowlState(this));
        }
    }
}
