package bowling.domain;

public class Spare extends Result {
    public Spare(Pins pins) {
        super(pins);
    }

    @Override
    public Condition getCondition() {
        return Condition.FINISH_PERFECT;
    }
}
