package bowling.domain;

public class NormalFrame implements Frame {
    private Score firstScore;
    private Score secondScore;
    private int frameNumber;

    public NormalFrame(Score firstScore) {
        this.firstScore = firstScore;
    }

    public void setSecondScore(Score secondScore) {
        this.secondScore = secondScore;
    }

    @Override
    public boolean isStrike() {
        if (firstScore.equals(new Score(10))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSpare() {
        if (firstScore.add(secondScore).equals(new Score(10))) {
            return true;
        }
        return false;
    }

    @Override
    public void setThirdScore(Score thirdScore) {
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    public boolean isFirstPitch() {
        if (secondScore == null) {
            return true;
        }
        return false;
    }
    public String convert() {
        if (isStrike()) {
            return "X";
        }
        if (isFirstPitch() && !isStrike()) {
            return firstScore.convert() + "|";
        }
        if (isSpare()) {
            return firstScore.convert() + "|" + "/";
        }
        return firstScore.convert() + "|" + secondScore.convert();
    }
}
