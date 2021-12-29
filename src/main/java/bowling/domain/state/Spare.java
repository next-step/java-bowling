package bowling.domain.state;

import static java.lang.Boolean.TRUE;

import bowling.domain.Pins;

public class Spare implements State {

    private static final String MARKING = "%d|/";

    private final Pins firstPins;
    private final Pins secondPins;


    public Spare(Pins firstPins, Pins secondPins) {
        valid(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State bowl(Pins pins) {
        throw new RuntimeException("이미 완료된 상태 입니다.");
    }

    @Override
    public String mark() {
        return String.format(MARKING, firstPins.fallenPins());
    }

    @Override
    public boolean isFinished() {
        return TRUE;
    }

    private void valid(Pins firstPins, Pins secondPins) {
        if (!firstPins.isSpare(secondPins)) {
            throw new IllegalArgumentException("스페어가 아닙니다.");
        }
    }

}
