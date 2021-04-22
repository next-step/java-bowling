package bowling.domain.concrete;

import bowling.domain.engine.Pins;
import bowling.domain.engine.PitchResult;
import bowling.domain.engine.Records;

public class FinalFrame {

    private static final int MAXIMUM_THROW_CHANCES = 3;

    private final Records records = new Records();
    private Pins pins = new Pins();

    public void throwBall(PitchResult pitchResult) {
        if (isEnded()) {
            throw new IllegalStateException("이미 프레임이 종료된 상태입니다.");
        }

        records.add(pitchResult);
        pins.knockDown(pitchResult);

        if (pins.isAllCleared()) {
            resetPins();
        }
    }

    public boolean isEnded() {
        return records.isMissed() || records.throwCounts() == MAXIMUM_THROW_CHANCES;
    }

    private void resetPins() {
        pins = new Pins();
    }


}
