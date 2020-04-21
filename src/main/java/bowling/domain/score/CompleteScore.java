package bowling.domain.score;

public class CompleteScore implements Score {
    private int score;

    public CompleteScore(int score) {
        this.score = score;
    }

    @Override public boolean isCompleted() {
        return true;
    }

    @Override public int getScore() {
        return score;
    }

    @Override public Score add(Score score) {
        if (!score.isCompleted()) {
            return EmptyScore.valueOf();
        }
        return new CompleteScore(this.score + score.getScore());
    }
}
