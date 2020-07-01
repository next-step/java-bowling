package bowling.domain;

import bowling.common.IntegerUtils;

public class NormalFrame extends Frame {

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pitch = new Pitch();
    }

    @Override
    public FrameState bowling(Pin pin) {
        pitch.add(pin);
        if (pitch.isFinish()) {
            return FrameState.ofNew();
        }
        return FrameState.ofNotFinish(pitch.getRemain());
    }

    @Override
    public ShotHistory getShotHistory() {
        return pitch.getShotHistory();
    }

    @Override
    public Score calculateScore() {
        Score score = Score.ofPitch(pitch);
        if (score.isNull()) {
            return score;
        }

        ShotHistory shotHistory = pitch.getShotHistory();
        if (shotHistory.contains(Shot.SPARE)) {
            Score bonus = nextFrame.calculateBonusScore(Shot.SPARE);
            if (bonus.equals(Score.ofNull())) {
                return Score.ofNull();
            }
            score = score.add(bonus);
        }
        if (shotHistory.contains(Shot.STRIKE)) {
            Score bonus = nextFrame.calculateBonusScore(Shot.STRIKE);
            if (bonus.equals(Score.ofNull())) {
                return Score.ofNull();
            }
            score = score.add(bonus);
        }
        return score;
    }

    @Override
    public Score calculateBonusScore(Shot shot) {
        if (pitch.getThrowCount() == IntegerUtils.ZERO ) {
            return Score.ofNull();
        }

        if (shot == Shot.SPARE) {
            return Score.of(pitch.calculatePinCount(1));
        }

        if (shot == Shot.STRIKE) {
            if (!pitch.isPitchEnd()) {
                return Score.ofNull();
            }
            if (pitch.getThrowCount() == 2) {
                return Score.of(pitch.calculatePinCount(2));
            }
            return Score.ofPitch(pitch).add(nextFrame.calculateBonusScore(Shot.SPARE));
        }

        throw new IllegalArgumentException("Bonus Shot Type is only Strike, Spare");
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }
}
