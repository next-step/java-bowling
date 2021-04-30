package bowling.domain;

import java.util.List;

public class FinalFrame extends Frame {

    private Pitch bonusPitch;

    protected FinalFrame(int number) {
        super(number);
    }

    private boolean hasBonusPitch() {
        return this.bonusPitch != null;
    }

    @Override
    public void pitch(Pitch pitch) {
        if (pitches().isStrike() || pitches().isSpare()) {
            this.bonusPitch = pitch;
            return;
        }
        pitches().add(pitch);
    }

    @Override
    public boolean isFinished() {
        return hasBonusPitch() || pitches().isMiss();
    }

    @Override
    public List<String> getScoreBoards() {
        List<String> scoreBoards = pitches().getScoreBoards();
        if (hasBonusPitch()) {
            scoreBoards.add(bonusPitch.toString());
        }
        return scoreBoards;
    }

    @Override
    public Frame next() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public int score() {
        return pitches().pinDownCount() + bonusScore();
    }

    private int bonusScore() {
        if (hasBonusPitch()) {
            return bonusPitch.value();
        }
        return NON_BONUS;
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
