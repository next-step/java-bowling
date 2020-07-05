package bowling.domain;

import bowling.domain.state.State;

public class NormalFrame extends Frame {

    protected NormalFrame(int frameNo) {
        super(frameNo);
    }

    @Override
    public State bowling(Pin pin) {
        pitch.add(pin);
        if (pitch.isFinish()) {
            return State.ofNew();
        }
        return State.ofSpare(pitch.getRemain());
    }

    @Override
    public Score calculateScore() {
        Score score = Score.ofPitch(pitch);
        if (score.isNull()) {
            return score;
        }

        Shot lastShot = pitch.getShotHistory().getLast();
        if (BONUS_SHOT.contains(lastShot)) {
            score = score.add(nextFrame.calculateBonusScore(lastShot));
        }
        return score;
    }

    @Override
    public Score calculateBonusScore(Shot shot) {
        if (shot.getBonusCount() <= pitch.getThrowCount()) {
            return Score.of(pitch.calculatePinCount(shot.getBonusCount()));
        }

        if (pitch.isFinish()) {
            return Score.ofPitch(pitch).add(nextFrame.calculateBonusScore(Shot.SPARE));
        }

        return Score.ofNull();
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }
}
