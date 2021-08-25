package bowling.domain.state;

import bowling.domain.pins.Pins;

public interface Finished extends State {

    @Override
    default State bowl(Pins pins) {
        throw new UnsupportedOperationException("지원하지 않는 동작입니다.");
    }

    @Override
    default boolean isFinish() {
        return true;
    }
}