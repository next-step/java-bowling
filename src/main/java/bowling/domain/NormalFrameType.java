package bowling.domain;

import java.util.ArrayList;

public class NormalFrameType implements FrameType {
    private final Pitches pitches;

    public NormalFrameType() {
        this.pitches = new Pitches(new ArrayList<>());
    }

    @Override
    public Pitches pitch(int point) {
        validatePitch(point);
        return pitches.pitch(point);
    }
    
    @Override
    public boolean isContinue() {
        return !pitches.isStrike() && !pitches.isEndPitch();
    }

    @Override
    public int count() {
        return pitches.count();
    }

    @Override
    public int sum() {
        return pitches().sum();
    }

    @Override
    public Pitches pitches() {
        return pitches;
    }

    private boolean isStrike(Pitches pitches) {
        return pitches.count() == 1 && pitches.sum() == 10;
    }

    private boolean isEndPitch(Pitches pitches) {
        return pitches.count() == 2;
    }

    private void validatePitch(int point) {
        if (pitches.sum() + point > 10) {
            throw new IllegalArgumentException("10개 이상의 핀을 쓰러트릴 수 없습니다.");
        }
    }
}
