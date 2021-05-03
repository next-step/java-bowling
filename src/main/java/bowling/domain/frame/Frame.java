package bowling.domain.frame;

import java.util.List;
import java.util.Optional;

public abstract class Frame {
    protected Scores scores;
    protected Frame nextFrame;

    public boolean isFinished() {
        return this.scores.isFinished();
    }

    public void addScore(int score) throws Exception {
        this.scores.addScore(score);
    }

    public List<Score> getScores() {
        return this.scores.getScores();
    }

    public abstract Optional<List<Score>> getTwoScores();


    public Optional<Score> getOneScore() {
        if (!this.scores.getScores().isEmpty()) {
            return Optional.of(this.scores.getScores().get(0));
        }
        return Optional.empty();
    }

    public void nextFrame(Frame nextFrame) {
        this.nextFrame = nextFrame;
    }

    public abstract Optional<Integer> frameScore();

}
