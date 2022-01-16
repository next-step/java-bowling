package bowling.domain.frame;

import bowling.domain.Score;

public class NormalFrame extends Frame {
    public static int firstScoreKey = 1;
    public static int secondScoreKey = 2;


    private Score firstScore;
    private Score secondScore;

    public NormalFrame() {
    }

    public NormalFrame(Score firstScore) {
        this.firstScore = firstScore;
    }

    @Override
    public void makeScore(Score score, int index) {
        if (index == firstScoreKey) {
            this.firstScore = score;
        }
        if (index == secondScoreKey) {
            this.secondScore = score;
        }
    }

    public boolean isStrike() {
        return firstScore.equals(new Score(10));
    }

    @Override
    public boolean isSpare(Score scoreA, Score scoreB) {
        return scoreA.add(scoreB).equals(new Score(10));
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    public boolean hasDoneFirstPitch() {
        return firstScore != null && secondScore == null;
    }

    @Override
    public boolean hasDoneSecondPitch() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return firstScore == null;
    }

    public String convert() {
        if (isEmpty()) {
            return "";
        }
        if (isStrike()) {
            return "X";
        }
        if (hasDoneFirstPitch() && !isStrike()) {
            return firstScore.convert() + "|";
        }
        if (isSpare(firstScore, secondScore)) {
            return firstScore.convert() + "|" + "/";
        }
        return firstScore.convert() + "|" + secondScore.convert();
    }
}
