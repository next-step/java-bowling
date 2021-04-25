package bowling.domain.concrete;

import bowling.domain.engine.Pins;
import bowling.domain.engine.PitchResult;
import bowling.domain.engine.Records;
import bowling.domain.engine.frame.Frame;
import bowling.dto.RecordsDto;

public class NormalFrame implements Frame {

    private final Pins pins = new Pins();
    private final Records records = new Records();

    @Override
    public void throwBall(PitchResult pitchResult) {
        if (isEnded()) {
            throw new IllegalStateException("이미 프레임이 종료된 상태입니다.");
        }

        records.save(pitchResult);
        pins.knockDown(pitchResult);
    }

    @Override
    public boolean isEnded() {
        return records.isStrike() || records.isMissed() || records.isSpare();
    }

    @Override
    public RecordsDto export() {
        return records.export();
    }
}
