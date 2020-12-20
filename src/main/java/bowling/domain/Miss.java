package bowling.domain;

public class Miss extends Result {
    public Miss(Pins pins) {
        super(pins);
    }

    @Override
    public Condition getCondition() {
        return Condition.FINISH_NOT_PERFECT;
    }
}
