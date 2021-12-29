package bowling.domain.state;

import static java.lang.Boolean.TRUE;

import bowling.domain.Pins;

public class Strike implements State {

    private static final String MARKING = "X";

    private final Pins pins;

    public Strike(Pins pins) {
        valid(pins);
        this.pins = pins;
    }

    @Override
    public State bowl(Pins pins) {
        throw new RuntimeException("이미 완료된 상태 입니다.");
    }

    @Override
    public String mark() {
        return MARKING;
    }

    @Override
    public boolean isFinished() {
        return TRUE;
    }

    private void valid(Pins pins) {
        if (!pins.isStrike()) {
            throw new IllegalArgumentException("스트라이크가 아닙니다.");
        }
    }

}
