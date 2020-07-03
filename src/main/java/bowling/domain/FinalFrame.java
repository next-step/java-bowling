package bowling.domain;

import bowling.domain.state.State;

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
    public State bowling(Pin pin) {
        if (isBonusPitch) {
            return bowlingBonus(pin);
        }

        pitch.add(pin);
        if (pitch.isFinish()) {
            return isNextBonusPitch();
        }
        return State.ofSpare(pitch.getRemain());
    }

    private State bowlingBonus(Pin pin) {
        bonusPitch = Pitch.ofBounus();
        bonusPitch.add(pin);
        return State.ofFinish();
    }

    private State isNextBonusPitch() {
        if (pitch.isStrikeOrSpare()) {
            isBonusPitch = true;
            return State.ofSpare(Pin.MAX_COUNT);
        }
        return State.ofFinish();
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
