package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    protected Scores scores;

    protected Frame(Scores scores) {
        this.scores = scores;
    }

    public static Frame empty() {
        return new Frame(Scores.empty());
    }

    public static Frame of(List<Score> scores) {
        return new Frame(Scores.of(scores));
    }

    public void record(int score) {
        scores = scores.add(score);
    }

    public boolean isFinished() {
        return scores.isFinished();
    }

    public List<Score> getScores() {
        return scores.getScores();
    }

    public Integer calculateScore(Integer previousFrameScore, List<Frame> nextFrames) {
        return scores.calculate(previousFrameScore, getNextScores(nextFrames));
    }

    private List<Score> getNextScores(List<Frame> nextFrames) {
        return nextFrames.stream()
                .flatMap(frame -> frame.getScores().stream())
                .collect(Collectors.toList());
    }
}
