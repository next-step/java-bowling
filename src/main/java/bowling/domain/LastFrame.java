package bowling.domain;

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
        if (secondScore == null) {
            return true;
        }
        return false;
    }

    public boolean isSecondPitch() {
        if (thirdScore == null && !isFirstPitch()) {
            return true;
        }
        return false;
    }

    @Override
    public String convert() {
        if (isFirstPitch()) {
            return firstScore.convert();
        }
        if (isSecondPitch() && isSpare()) {
            return firstScore.convert() + "|" + "/";
        }
        if (isSecondPitch() && !isSpare()) {
            return firstScore.convert() + "|" + secondScore.convert();
        }
        return firstScore.convert() + "|" + secondScore.convert() + "|" + thirdScore.convert();
    }

    @Override
    public boolean isStrike() {
        return firstScore.isStrike();
    }

    @Override
    public boolean isSpare() {
        return firstScore.add(secondScore).equals(new Score(10));
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
