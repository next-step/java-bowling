package bowling.domain;

public class FinalFrame extends Frame {

    private static final int BONUS_LIMIT = 2;

    protected FinalFrame(int number) {
        super(number);
    }

    @Override
    public void pitch(Pitch pitch) {
        if (pitches().isEmpty() && pitch.isStrike()) {
            pitches().increasePitchAbleCount();
        }
        pitches().add(pitch);
        if (pitches().isSpare()) {
            pitches().increasePitchAbleCount();
        }
    }

    @Override
    public boolean isFinished() {
        return pitches().isFinished();
    }

    @Override
    public Frame next() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public int score() {
        return pitches().pinDownCount();
    }

    @Override
    public int bonusScore(Pitches beforePitches) {
        if (beforePitches.isStrike()) {
            return pitches().pinDownCount(BONUS_LIMIT);
        }
        if (beforePitches.isSpare()) {
            return pitches().firstPinDownCount();
        }
        return NON_BONUS;
    }

    @Override
    public int doubleBonusScore() {
        return pitches().firstPinDownCount();
    }

    @Override
    public boolean isScoreDecided() {
        return pitches().isFinished();
    }

    @Override
    public boolean isBonusScoreDecided(Pitches beforePitches) {
        if (beforePitches.isStrike()) {
            return pitches().isFinished();
        }
        if (beforePitches.isSpare()) {
            return !pitches().isEmpty();
        }
        return false;
    }

}
