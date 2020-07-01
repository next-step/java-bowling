package bowling.domain;

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

        if (pitch.isPitchEnd()) {
            return Score.ofPitch(pitch).add(nextFrame.calculateBonusScore(Shot.SPARE));
        }

        return Score.ofNull();
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }
}
