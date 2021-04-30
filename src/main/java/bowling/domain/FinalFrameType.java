package bowling.domain;

import java.util.ArrayList;

public class FinalFrameType implements FrameType {
    private static final int MAX_PITCHES = 3;

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
    public boolean isContinue() {
        return !(pitches.isOpen() || pitches.isLastPitch(MAX_PITCHES));
    }

    @Override
    public Pitches pitches() {
        return pitches;
    }
}
