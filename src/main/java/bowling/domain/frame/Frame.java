package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;

public class Frame {
    private final FrameNumber frameNumber;
    private Scores scores;

    private Frame(FrameNumber frameNumber, Scores scores) {
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

    public int getScoresCount() {
        return scores.getScores().size();
    }

    public int getScore(int tryCount) {
        return scores.getScore(tryCount).getScore();
    }

    public boolean needNextScores() {
        return !frameNumber.isLast() && isFinished() && (scores.isFirstStrike() || scores.isSecondSpare());
    }

    public int getNecessaryNextScoresCount() {
        if (frameNumber.isLast()) {
            return 0;
        }
        if (scores.isFirstStrike()) {
            return 2;
        }
        if (scores.isSecondSpare()) {
            return 1;
        }
        return 0;
    }
}
