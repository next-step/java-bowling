package bowling.domain;

import bowling.domain.state.State;

public class FinalFrame extends Frame {
    private Pitch bonusPitch;

    protected FinalFrame() {
        super(FINAL_FRAME);
        this.bonusPitch = Pitch.ofBounus();
    }

    @Override
    public State bowling(Pin pin) {
        if (pitch.isStrikeOrSpare()) {
            return bowlingBonus(pin);
        }

        pitch.add(pin);
        return getCurrentState();
    }

    private State bowlingBonus(Pin pin) {
        if (bonusPitch.isFinish()) {
            throw new IllegalArgumentException("bonus pitch is already finish");
        }
        bonusPitch = Pitch.ofBounus();
        bonusPitch.add(pin);
        return State.ofFinish();
    }

    private State getCurrentState() {
        if (pitch.isStrikeOrSpare()) {
            return State.ofSpare(Pin.MAX_COUNT);
        }
        if (pitch.isFinish()) {
            return State.ofFinish();
        }
        return State.ofSpare(pitch.getRemain());
    }

    @Override
    public ShotHistory getShotHistory() {
        ShotHistory shotHistory = pitch.getShotHistory();
        if (pitch.isStrikeOrSpare()) {
            return shotHistory.add(bonusPitch.getShotHistory());
        }
        return shotHistory;
    }

    @Override
    public Score calculateScore() {
        if (!isGameEnd()) {
            return Score.ofNull();
        }

        Score score = Score.ofPitch(pitch);
        if (pitch.isStrikeOrSpare()) {
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
        if (pitch.isStrikeOrSpare()) {
            return bonusPitch.isFinish();
        }
        return pitch.isFinish();
    }
}
