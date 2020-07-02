package bowling.domain;

public class FinalFrame extends Frame {
    private boolean isBonusPitch;
    private Pitch bonusPitch;

    private FinalFrame() {
        super(FINAL_FRAME);
        this.isBonusPitch = false;
        this.bonusPitch = Pitch.ofBounus();
    }

    public static FinalFrame last() {
        return new FinalFrame();
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
        bonusPitch = Pitch.ofBounus();
        bonusPitch.add(pin);
        return FrameState.ofFinish();
    }

    private FrameState isNextBonusPitch() {
        if (pitch.isStrikeOrSpare()) {
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
            score = score.add(Score.of(bonusPitch.getFallenPin()));
        }
        return score;
    }

    @Override
    public Score calculateBonusScore(Shot shot) {
        if (shot.getBonusCount() <= pitch.getThrowCount()) {
            return Score.of(pitch.calculatePinCount(shot.getBonusCount()));
        }

        if (pitch.isFinish()) {
            return Score.of(pitch.calculatePinCount(pitch.getThrowCount()));
        }

        return Score.ofNull();
    }

    @Override
    public boolean isGameEnd() {
        if (isBonusPitch) {
            return bonusPitch.isFinish();
        }
        return pitch.isFinish();
    }
}
