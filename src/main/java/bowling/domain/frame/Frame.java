package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;

public class Frame {
    private final FrameNumber frameNumber;
    private Scores scores;

    public Frame(FrameNumber frameNumber, Scores scores) {
        this.frameNumber = frameNumber;
        this.scores = scores;
    }

    public static Frame first() {
        return new Frame(FrameNumber.first(), Scores.empty());
    }

    public static Frame of(int frameNumber, List<Score> scores) {
        return new Frame(FrameNumber.of(frameNumber), Scores.of(scores));
    }

    public int getFrameNumber() {
        return frameNumber.getNumber();
    }

    public Frame next() {
        return new Frame(frameNumber.next(), Scores.empty());
    }

    public void record(int score) {
        if (isFinished()) {
            throw new InvalidFrameRecordActionException();
        }
        scores = scores.add(score, frameNumber.isLast());
    }

    public boolean isFinished() {
        return scores.isFinished(frameNumber.isLast());
    }

    public List<Score> getScores() {
        return scores.getScores();
    }
}
