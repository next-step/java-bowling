package bowling.domain.frame;

import bowling.domain.score.LastScores;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;
import java.util.stream.Collectors;

public class Frame {
    private final FrameNumber frameNumber;
    private Scores scores;

    private Frame(FrameNumber frameNumber, Scores scores) {
        this.frameNumber = frameNumber;
        this.scores = getScores(frameNumber, scores);
    }

    private Scores getScores(FrameNumber frameNumber, Scores scores) {
        if (frameNumber.isLast()) {
            return LastScores.of(scores.getScores());
        }
        return scores;
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
