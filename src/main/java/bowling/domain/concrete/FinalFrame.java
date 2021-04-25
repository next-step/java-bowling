package bowling.domain.concrete;

import bowling.domain.engine.Pins;
import bowling.domain.engine.PitchResult;
import bowling.domain.engine.Records;
import bowling.domain.engine.frame.Frame;
import bowling.dto.RecordsDto;

public class FinalFrame implements Frame {

    private static final int MAXIMUM_THROW_CHANCES = 3;

    private final Records records = new Records();
    private final Pins pins = new Pins();

    @Override
    public void throwBall(PitchResult pitchResult) {
        if (isEnded()) {
            throw new IllegalStateException("이미 프레임이 종료된 상태입니다.");
        }

        records.save(pitchResult);
        pins.knockDown(pitchResult);

        if (pins.isAllCleared()) {
            pins.reset();
        }
    }

    @Override
    public boolean isEnded() {
        return records.isMissed() || records.throwCounts() == MAXIMUM_THROW_CHANCES;
    }

    @Override
    public RecordsDto export() {
        return records.export();
    }
}
