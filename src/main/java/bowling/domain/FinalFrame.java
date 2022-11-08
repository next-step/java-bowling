package bowling.domain;

public class FinalFrame extends Frame {
    private final Scores bonusScores;

    public FinalFrame() {
        this.bonusScores = new Scores();
    }

    @Override
    public boolean isEndFrame() {
        if (isBonus()) {
            return bonusScores.getTryCount() >= 1;
        }
        return super.isEndFrame();
    }

    @Override
    public void record(int downPinCount) {
        if (isBonus()) {
            bonusScores.record(downPinCount);
            return;
        }

        scores.record(downPinCount);
    }

    @Override
    public boolean isValidBonusGameScore() {
        return bonusScores.getTryCount() != 0;
    }

    @Override
    public int getPinScore() {
        return scores.getPinScore() + bonusScores.sumOfDownPins();
    }

    @Override
    public int getSecondPitchScore() {
        if (FrameScore.STRIKE.equals(getResult())) {
            return bonusScores.getFirstScore();
        }
        return scores.getSecondPitchScore();
    }

    @Override
    public int getTryCount() {
        return scores.getTryCount() + bonusScores.getTryCount();
    }

    @Override
    public boolean isValidBonusScore() {
        return false;
    }

    public Scores getBonusScores() {
        return bonusScores;
    }

    private boolean isBonus() {
        return scores.isStrike() || scores.isSpare();
    }

}
