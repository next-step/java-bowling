package bowling.domain.score;

import bowling.domain.frame.Pitch;

import java.util.Stack;

public class Scores {
    private final Stack<Score> scores;

    private Scores() {
        this.scores = new Stack<>();
    }

    public static Scores init() {
        return new Scores();
    }

    public boolean turnOver() {
        if (scores.isEmpty()) {
            return false;
        }
        if (currentScore().isProgress()) {
            return false;
        }
        return true;
    }

    public void score(Pitch pitch) {
        if (scores.isEmpty()) {
            Score lastScore = Score.from(pitch);
            scores.add(lastScore);
            return;
        }

        Score currentScore = currentScore();

        if (currentScore.isProgress()) {
            if (isLastScore()) {
                addBonusSpareScore(pitch.getPitch());

                currentScore.addScore(pitch);
                addLastFrameBonus(currentScore);

                return;
            }
            addBonusSpareScore(pitch.getPitch());
            currentScore.addScore(pitch);
            addBonusStrikeScore(currentScore);
            return;
        }

        if (isBonusScore()) {
            Score bonusScore = Score.lastScore(pitch);
            addLastBonusStrikeScore(bonusScore);
            addBonusSpareScore(pitch.getPitch());
            scores.add(bonusScore);
            return;
        }

        if (pitch.isStrike()) {
            addBonusSpareScore(pitch.getPitch());
            scores.add(Score.from(pitch));
            addBonusStrikeScore(currentScore);
            return;
        }

        if (currentScore.isDone()) {
            scores.add(Score.from(pitch));
            return;
        }

        if (currentScore.isSpare()) {
            addBonusSpareScore(pitch.getPitch());
            scores.add(Score.from(pitch));
            return;
        }

        scores.add(Score.from(pitch));
    }

    private void addLastFrameBonus(Score lastScore) {
        Score checkStrikeScore = scores.get(scores.size() - 3);
        Score beforeScore = scores.get(scores.size() - 2);

        if (checkStrikeScore.isStrike()) {
            checkStrikeScore.addStrikeBonusScore(beforeScore);
            checkStrikeScore.addStrikeBonusScore(lastScore);
        }
        if (beforeScore.isStrike()) {
            beforeScore.addStrikeBonusScore(lastScore);
        }
    }

    private void addLastBonusStrikeScore(Score lastScore) {
        Score checkStrikeScore = scores.get(scores.size() - 2);
        Score beforeScore = scores.get(scores.size() - 1);

        if (checkStrikeScore.isStrike()) {
            checkStrikeScore.addStrikeBonusScore(beforeScore);
            checkStrikeScore.addStrikeBonusScore(lastScore);
        }
        if (beforeScore.isStrike()) {
            beforeScore.addStrikeBonusScore(lastScore);
        }
    }

    private void addBonusStrikeScore(Score score) {
        if (scores.size() < 3) {
            return;
        }

        Score checkStrikeScore = scores.get(scores.size() - 3);
        Score beforeScore = scores.get(scores.size() - 2);

        if (checkStrikeScore.isStrike()) {
            checkStrikeScore.addStrikeBonusScore(beforeScore);
            checkStrikeScore.addStrikeBonusScore(score);
        }
    }

    private void addBonusSpareScore(int bonusScore) {
        Score spareScore = scores.peek();
        if (spareScore.isSpare()) {
            spareScore.addSpareBonusScore(bonusScore);
        }
    }

    private boolean isLastScore() {
        return scores.size() == 10;
    }

    private boolean isBonusScore() {
        return scores.size() == 10;
    }

    private Score currentScore() {
        return scores.peek();
    }

    public Stack<Score> getScores() {
        return scores;
    }

    public int totalScore() {
        int totalScore = 0;
        while (!scores.isEmpty()) {
            totalScore += scores.pop().getScore();
        }
        return totalScore;
    }
}
