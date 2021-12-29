package bowling.domain.state;

import static java.lang.Boolean.FALSE;

import bowling.domain.Pins;

public class FirstBowl implements State {

    private static final String MARKING = "%d";

    private final Pins firstPins;

    public FirstBowl(Pins pins) {
        valid(pins);
        this.firstPins = pins;
    }

    @Override
    public State bowl(Pins pins) {
        if (this.firstPins.isSpare(pins)) {
            return new Spare(firstPins, pins);
        }

        return new Mess(firstPins, pins);
    }

    @Override
    public String mark() {
        return String.format(MARKING, firstPins.fallenPins());
    }

    @Override
    public boolean isFinished() {
        return FALSE;
    }

    private void valid(Pins pins) {
        if (pins.isStrike()) {
            throw new IllegalArgumentException("스트라이크 핀은 만들 수 없습니다.");
        }
    }

}
