package bowling.domain.frame;

import bowling.domain.score.Scores;

public class NormalFrame extends Frame {
    private final static int MAX_SCORE = 10;

    public NormalFrame() {
        super(new Scores());
    }

    @Override
    public void validateScores() {
        if (scores.totalScore() > MAX_SCORE) {
            throw new IllegalArgumentException("score less than 10");
        }
    }

    @Override
    public boolean availablePlay() {
        if (scores.isStrikeOrSpare()) {
            return false;
        }
        return scores.firstScore();
    }
}
