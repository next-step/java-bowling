package bowling.domain.frame;

import bowling.domain.score.Scores;

public abstract class Frame {
    protected Scores scores;

    protected Frame(Scores scores) {
        this.scores = scores;
    }

    public void addPoint(int point) {
        scores.addScore(point);
        validateScores();
    }

    public int totalScore() {
        return scores.totalScore();
    }

    public abstract void validateScores();

    public abstract boolean availablePlay();
}
