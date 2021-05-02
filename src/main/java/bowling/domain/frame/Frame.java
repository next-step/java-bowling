package bowling.domain.frame;

import java.util.List;

public abstract class Frame {
    protected Scores scores;
    private Frame nextFrame;

    public boolean isFinished() {
        return this.scores.isFinished();
    }

    public void addScore(int score) throws Exception {
        this.scores.addScore(score);
    }

    public List<Score> getScores() {
        return this.scores.getScores();
    }

    public void nextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    public abstract int frameScore();

}
