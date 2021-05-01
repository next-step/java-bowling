package bowling.domain;

import java.util.List;

public class FinalFrame extends Frame {

    protected FinalFrame(int number) {
        super(number);
    }

    @Override
    public void pitch(Pitch pitch) {
        if (pitches().isSpare()) {
            pitches().increasePitchAbleCount();
        }
        if (pitches().isEmpty() && pitch.isStrike()) {
            pitches().increasePitchAbleCount();
        }
        pitches().add(pitch);
    }

    @Override
    public boolean isFinished() {
        return pitches().isFinished();
    }

    @Override
    public List<String> getScoreBoards() {
        return pitches().getScoreBoards();
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
        return NON_BONUS;
    }

    @Override
    public int doubleBonusScore() {
        return NON_BONUS;
    }

}
