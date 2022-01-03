package bowling.domain.state;

import bowling.domain.Pins;

public class Strike extends Finished {

    private static final String MARKING = "X";

    private final Pins pins;

    public Strike(Pins pins) {
        valid(pins);
        this.pins = pins;
    }

    @Override
    public String mark() {
        return MARKING;
    }

    private void valid(Pins pins) {
        if (!pins.isStrike()) {
            throw new IllegalArgumentException("스트라이크가 아닙니다.");
        }
    }

}
