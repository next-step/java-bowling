package bowling.domain;

import bowling.common.IntegerUtils;

public class FinalFrame extends Frame {
    private boolean isBonusPitch;
    private Pitch bonusPitch;

    public FinalFrame() {
        this.frameNo = FINAL_FRAME;
        this.pitch = new Pitch();
        this.isBonusPitch = false;
        this.bonusPitch = new Pitch();
    }

    @Override
    public FrameState bowling(Pin pin) {
        if (isBonusPitch) {
            return bowlingBonus(pin);
        }

        pitch.add(pin);
        if (pitch.isFinish()) {
            return isNextBonusPitch();
        }
        return FrameState.ofNotFinish(pitch.getRemain());
    }

    private FrameState bowlingBonus(Pin pin) {
        bonusPitch = new Pitch();
        bonusPitch.add(pin);
        return FrameState.ofFinish();
    }

    private FrameState isNextBonusPitch() {
        if (pitch.getRemain() == IntegerUtils.ZERO) {
            isBonusPitch = true;
            return FrameState.ofNotFinish(Pin.MAX_COUNT);
        }
        return FrameState.ofFinish();
    }

    @Override
    public ShotHistory getShotHistory() {
        ShotHistory shotHistory = pitch.getShotHistory();
        if (isBonusPitch) {
            shotHistory.add(bonusPitch.getShotHistory());
        }
        return shotHistory;
    }

    @Override
    public Score calculateScore() {
        if (!isGameEnd()) {
            return Score.ofNull();
        }
        Score score = Score.ofPitch(pitch);
        if (isBonusPitch) {
            return score.add(Score.of(bonusPitch.getFallenPin()));
        }
        return score;
    }

    @Override
    public Score calculateBonusScore(Shot shot) {
        if (pitch.getThrowCount() == IntegerUtils.ZERO ) {
            return Score.ofNull();
        }

        if (shot.getBonusCount() <= pitch.getThrowCount()) {
            return Score.of(pitch.calculatePinCount(shot.getBonusCount()));
        }

        if (shot == Shot.STRIKE && pitch.isPitchEnd()) {
            return Score.of(pitch.calculatePinCount(pitch.getThrowCount()));
        }

        return Score.ofNull();
    }

    @Override
    public boolean isGameEnd() {
        if (isBonusPitch) {
            return bonusPitch.getThrowCount() > IntegerUtils.ZERO;
        }
        return pitch.isFinish();
    }
}
