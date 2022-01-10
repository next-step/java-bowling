package bowling.domain.frame;

import bowling.domain.Score;

public class NormalFrame implements Frame {
    private Score firstScore;
    private Score secondScore;

    private NormalFrame() {
    }

    public NormalFrame(Score firstScore) {
        this.firstScore = firstScore;
    }

    public void setSecondScore(Score secondScore) {
        this.secondScore = secondScore;
    }

    private boolean isStrike() {
        return firstScore.equals(new Score(10));
    }

    @Override
    public boolean isSpare(Score scoreA, Score scoreB) {
        return scoreA.add(scoreB).equals(new Score(10));
    }

    @Override
    public void setThirdScore(Score thirdScore) {
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    public boolean isFirstPitch() {
        return secondScore == null;
    }

    public String convert() {
        if (isStrike()) {
            return "X";
        }
        if (isFirstPitch() && !isStrike()) {
            return firstScore.convert() + "|";
        }
        if (isSpare(firstScore, secondScore)) {
            return firstScore.convert() + "|" + "/";
        }
        return firstScore.convert() + "|" + secondScore.convert();
    }
}
