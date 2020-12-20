package bowling.domain;

public class Strike extends Result {
    public Strike(Pins pins) {
        super(pins);
    }

    @Override
    public Condition getCondition() {
        return Condition.FINISH_PERFECT;
    }
}
