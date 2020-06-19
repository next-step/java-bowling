package bowling.step4.domain.frame;

import bowling.step4.domain.Frames;
import bowling.step4.domain.ScoresType;
import bowling.step4.domain.scores.NormalScores;
import bowling.step4.domain.scores.Scores;
import bowling.step4.domain.scores.FinalScores;

import java.util.Objects;
import java.util.stream.Stream;

public class NormalFrame extends Frame {
    private Frame nextFrame;

    private NormalFrame(int frame, Scores scores, Frame nextFrame) {
        super(frame, scores);
        this.nextFrame = nextFrame;
    }

    public static Frame of(int frame, Scores scores, Frame nextFrame) {
        return new NormalFrame(frame, scores, nextFrame);
    }

    public static Frame start() {
        return of(Frames.START_FRAME, NormalScores.init(), null);
    }

    public void createNextFrameOfScores(Scores scores) {
        this.scores = scores;
        if (!ScoresType.FULL.of(scores)) {
            return;
        }
        int nextFrameValue = frame + 1;
        this.nextFrame = nextFrameValue < Frames.LAST_FRAME
            ? of(nextFrameValue, NormalScores.init(), null)
            : FinalFrame.of(nextFrameValue, FinalScores.init());
    }

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }

    @Override
    protected int calculateScoreOfSpared() {
        if (nextFrame == null || ScoresType.EMPTY.of(nextFrame.scores)) {
            return EMPTY_CALC;
        }
        return nextFrame.scores
                        .stream()
                        .limit(1)
                        .reduce(scores.totalScore(), (total, score) -> total + score.getValue(), Integer::sum);
    }

    @Override
    protected int calculateScoreOfStrike() {
        if (nextFrame == null || !ScoresType.FULL.of(nextFrame.scores)) {
            return EMPTY_CALC;
        }
        if (ScoresType.STRIKE.of(nextFrame.scores)) {
            return nextFrame.calculateScoreOfTwoStrike(scores.totalScore());
        }
        return scores.totalScore() + nextFrame.scores.totalScore();
    }

    @Override
    protected int calculateScoreOfTwoStrike(int totalScore) {
        if (ScoresType.EMPTY.of(nextFrame.scores)) {
            return EMPTY_CALC;
        }
        return Stream.concat(scores.stream(), nextFrame.scores.stream())
                     .filter(Objects::nonNull)
                     .limit(2)
                     .reduce(totalScore, (total, score) -> total + score.getValue(), Integer::sum);
    }
}