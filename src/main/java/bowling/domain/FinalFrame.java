package bowling.domain;

public class FinalFrame extends Frame {
    private boolean bonus;
    private Scores bonusScores = new Scores();

    FinalFrame() {
        this.bonus = false;
    }

    public boolean isEndFrame() {
        if (isBonus()) {
            return false;
        }
        return super.isEndFrame();
    }

    public void record(int downPinCount) {
        if (isBonus()) {
            bonus = true;
            bonusScores.record(downPinCount);
            return;
        }

        scores.record(downPinCount);
    }

    public boolean isValidBonusScore() {
        return bonusScores.getTryCount() != 0;
    }

    public Scores getBonusScores() {
        return bonusScores;
    }

    public int getPinScore() {
        return scores.getPinScore() + bonusScores.sumOfDownPins();
    }

    private boolean isBonus() {
        return !bonus && (scores.isStrike() || scores.isSpare());
    }

    public int getSecondPitchScore() {
        if (FrameScore.STRIKE.equals(getResult())) {
            return bonusScores.getFirstScore();
        }
        return scores.getSecondPitchScore();
    }
}
