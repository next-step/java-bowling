package bowling.domain.state;

import bowling.domain.value.Pins;

public class Ready extends ProgressState {

    private static final String READY_MARK = "";

    @Override
    public State bowl(Pins pins) {

        if(pins.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(pins);
    }

    @Override
    public String getMark() {
        return READY_MARK;
    }
}
