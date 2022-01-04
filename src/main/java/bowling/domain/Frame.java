package bowling.domain;

public class Frame {
    private Score firstScore;
    private Score secondScore;
    private Score thirdScore;

    public Frame(Score firstScore) {
        this.firstScore = firstScore;
    }

    public void setSecondScore(Score secondScore) {
        this.secondScore = secondScore;
    }

    private boolean isStrike() {
        if (firstScore.isStrike()) {
            return true;
        }
        return false;
    }

    private boolean isSpare() {
        if (!firstScore.isStrike() && firstScore.add(secondScore).equals(new Score(10))) {
            return true;
        }
        return false;
    }

    public String convertScore() {
        if (isStrike()) {
            return "X";
        }
        if (isSpare()) {
            return firstScore + "|" + "/";
        }
        if (firstScore.equals(new Score(0))) {

        }
        return firstScore + "|" + secondScore;
    }
}
