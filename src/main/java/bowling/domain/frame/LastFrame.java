package bowling.domain.frame;

import bowling.domain.Score;

public class LastFrame extends Frame {


    public LastFrame() {
    }

    public LastFrame(Score firstScore) {
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
        if (index == thirdScoreKey) {
            this.thirdScore = score;
        }
    }

    public boolean hasDoneFirstPitch() {
        return this.firstScore != null;
    }

    public boolean hasDoneSecondPitch() {
        return thirdScore == null && firstScore != null && secondScore != null;
    }

    @Override
    public String convert() {
        if (isEmpty()) {
            return "";
        }
        if (hasDoneFirstPitch() && secondScore == null && thirdScore == null) {
            return firstScore.convert() + "|";
        }

        if (hasDoneSecondPitch() && isSpare(firstScore, secondScore) && thirdScore == null) {
            return firstScore.convert() + "|" + "/|";
        }
        if (hasDoneSecondPitch() && !isSpare(firstScore, secondScore)) {
            return firstScore.convert() + "|" + secondScore.convert();
        }
        if (hasDoneSecondPitch() && isSpare(firstScore, secondScore) && thirdScore != null) {
            return firstScore.convert() + "|" + "/|" + "|" + thirdScore.convert();
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
    public boolean isLastFrame() {
        return true;
    }
}
