package bowling.domain;

public class FinalFrame extends Frame {
    private final Scores bonusScores;

    public FinalFrame() {
        this.bonusScores = new Scores();
    }

    public boolean isEndFrame() {
        if (isBonus()) {
            return bonusScores.getTryCount() >= 1;
        }
        return super.isEndFrame();
    }

    public void record(int downPinCount) {
        if (isBonus()) {
            bonusScores.record(downPinCount);
            return;
        }

        scores.record(downPinCount);
    }

    public boolean isValidBonusGameScore() {
        return bonusScores.getTryCount() != 0;
    }

    public Scores getBonusScores() {
        return bonusScores;
    }

    public int getPinScore() {
        return scores.getPinScore() + bonusScores.sumOfDownPins();
    }

    public int getSecondPitchScore() {
        if (FrameScore.STRIKE.equals(getResult())) {
            return bonusScores.getFirstScore();
        }
        return scores.getSecondPitchScore();
    }

    public int getTryCount() {
        return scores.getTryCount() + bonusScores.getTryCount();
    }

    public boolean isValidBonusScore() {
        return false;
    }

    private boolean isBonus() {
        return scores.isStrike() || scores.isSpare();
    }

}
