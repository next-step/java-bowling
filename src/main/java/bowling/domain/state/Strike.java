package bowling.domain.state;

import bowling.domain.Pins;

public class Strike implements State{
    private final Pins pins;

    public Strike(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State nextPitch(int pins) {
        throw new IllegalArgumentException("스트라이크는 더 이상 투구할 수 없습니다.");
    }

    @Override
    public String display() {
        return pins.display();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
