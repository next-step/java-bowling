package bowling.domain;

import java.util.ArrayList;

public class FinalFrameType implements FrameType {
    private final Pitches pitches;

    public FinalFrameType() {
        this.pitches = new Pitches(new ArrayList<>());
    }

    @Override
    public Pitches pitch(int point) {
        validatePitch(point);
        return pitches.pitch(point);
    }

    private void validatePitch(int point) {
        if (pitches.sum() + point > 30) {
            throw new IllegalArgumentException("30개 이상의 핀을 쓰러트릴 수 없습니다.");
        }
    }

    @Override
    public boolean isContinue(Pitches pitches) {
        return !isOpen(pitches) && !isEndPitch(pitches);
    }

    @Override
    public boolean isContinue() {
        return !pitches.isEndPitchFinal();
    }

    private boolean isOpen(Pitches pitches) {
        return pitches.count() == 2 && pitches.sum() < 10;
    }

    private boolean isEndPitch(Pitches pitches) {
        return pitches.count() == 3;
    }
}
