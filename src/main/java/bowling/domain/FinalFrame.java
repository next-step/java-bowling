package bowling.domain;

public class FinalFrame implements FrameType {
    private final Pitches pitches;

    public FinalFrame() {
        this.pitches = new Pitches();
    }

    @Override
    public Pitches pitch(int point) {
        validatePitch(point);
        return pitches.pitch(point);
    }

    @Override
    public boolean isContinue() {
        return !pitches.isEnd() || (pitches.isEnd() && pitches.hasBonusPitch());
    }

    @Override
    public Pitches pitches() {
        return pitches;
    }

    @Override
    public FrameScore frameScore() {
        return new FrameScore(new Score(pitches.sum()), pitches.bonusPitch());
    }

    private void validatePitch(int point) {
        if (pitches.sum() + point > 30) {
            throw new IllegalArgumentException("30개 이상의 핀을 쓰러트릴 수 없습니다.");
        }
    }
}
