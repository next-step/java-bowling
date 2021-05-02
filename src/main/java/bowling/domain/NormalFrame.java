package bowling.domain;

public class NormalFrame implements FrameType {
    private static final int MAX_PITCHES = 2;

    private final Pitches pitches;

    public NormalFrame() {
        this.pitches = new Pitches();
    }

    @Override
    public Pitches pitch(int point) {
        validatePitch(point);
        return pitches.pitch(point);
    }

    private void validatePitch(int point) {
        if (pitches.sum() + point > 10) {
            throw new IllegalArgumentException("10개 이상의 핀을 쓰러트릴 수 없습니다.");
        }
    }

    @Override
    public boolean isContinue() {
        return !(pitches.isEnd() || pitches.isLastPitch(MAX_PITCHES));
    }

    @Override
    public Pitches pitches() {
        return pitches;
    }
}
