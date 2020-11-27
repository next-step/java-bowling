package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;
import java.util.Optional;

public class Frame {
    private final FrameNumber frameNumber;
    private Scores scores;
    private Frame next;

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
        if (frameNumber.isLast()) {
            return null;
        }
        if (next == null) {
            next = new Frame(frameNumber.next(), Scores.empty());
        }
        return next;
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

    public Integer calculateScore(Integer previousFrameScore) {
        if (needNextScores()) {
            return calculateScoreWithNext(previousFrameScore);
        }
        return calculateWithoutNext(previousFrameScore);
    }

    private int calculateWithoutNext(Integer previousFrameScore) {
        return previousFrameScore + scores.sum();
    }

    public boolean needNextScores() {
        return !frameNumber.isLast() && isFinished() && scores.needNextScores();
    }

    private Integer calculateScoreWithNext(Integer previousFrameScore) {
        if (hasNoNextScores()) {
            return null;
        }
        return calculateWithoutNext(previousFrameScore) + getNextScores();
    }

    private boolean hasNoNextScores() {
        return getNecessaryNextScoresCount() > getCurrentNextScoresCount();
    }

    private int getNecessaryNextScoresCount() {
        return scores.getNecessaryNextScoresCount();
    }

    private Integer getCurrentNextScoresCount() {
        return next.getScoresCount() + Optional.ofNullable(next.next())
                .map(Frame::getScoresCount)
                .orElse(0);
    }

    private Integer getNextScores() {
        int necessaryNextScoresCount = getNecessaryNextScoresCount();
        if (necessaryNextScoresCount == 1) {
            return next.getScore(1);
        }
        if (necessaryNextScoresCount == 2) {
            return getNextTwoScores();
        }
        return 0;
    }

    private Integer getNextTwoScores() {
        if (next.getScoresCount() == 1) {
            return next.getScore(1) + Optional.ofNullable(next.next())
                    .map(frame -> frame.getScore(1))
                    .orElse(0);
        }
        return next.getScore(1) + next.getScore(2);
    }
}
