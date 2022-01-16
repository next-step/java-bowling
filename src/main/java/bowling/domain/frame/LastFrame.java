package bowling.domain.frame;

import bowling.domain.Score;

public class LastFrame implements Frame {
    private Score firstScore;
    private Score secondScore;
    private Score thirdScore;

    private LastFrame() {
    }

    public LastFrame(Score firstScore) {
        this.firstScore = firstScore;
    }

    @Override
    public void setSecondScore(Score secondScore) {
        this.secondScore = secondScore;
    }

    public boolean isFirstPitch() {
        return secondScore == null;
    }

    public boolean isSecondPitch() {
        return thirdScore == null && !isFirstPitch();
    }

    @Override
    public String convert() {
        if (isFirstPitch()) {
            return firstScore.convert() + "|";
        }
        if (isSecondPitch() && isSpare(firstScore, secondScore)) {
            return firstScore.convert() + "|" + "/|";
        }
        if (isSecondPitch() && !isSpare(firstScore, secondScore)) {
            return firstScore.convert() + "|" + secondScore.convert();
        }
        if (!secondScore.isStrike() && isSpare(secondScore, thirdScore)) {
            return firstScore.convert() + "|" + secondScore.convert() + "|/";
        }
        return firstScore.convert() + "|" + secondScore.convert() + "|" + thirdScore.convert();
    }

    @Override
    public boolean isSpare(Score scoreA, Score scoreB) {
        if (scoreA.isStrike() || scoreB.isStrike()) {
            return false;
        }
        return scoreA.add(scoreB).equals(new Score(10));
    }

    @Override
    public void setThirdScore(Score thirdScore) {
        this.thirdScore = thirdScore;
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }

}
