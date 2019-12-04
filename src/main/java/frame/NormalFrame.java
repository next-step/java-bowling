package frame;

import frame.info.FrameInfo;
import score.ScoreInfo;
import score.ScoreInfoBundle;
import score.Status;
import score.framescore.FrameScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static frame.info.FrameNumber.FIRST_FRAME_NUMBER;

public class NormalFrame implements Frame {
    private static final int FULL_TRY = 2;

    private final ScoreInfoBundle scores;
    private FrameInfo frameInfo;

    public NormalFrame(int frameNumber, List<ScoreInfo> scores) {
        this.scores = new ScoreInfoBundle(scores);
        this.frameInfo = FrameInfo.make(frameNumber);
    }

    public static NormalFrame firstNormalFrame() {
        return new NormalFrame(FIRST_FRAME_NUMBER, new ArrayList<>());
    }

    @Override
    public Frame nextFrame() {
        NormalFrame nextFrame = new NormalFrame(frameInfo.nextNumber(), new ArrayList<>());
        frameInfo = frameInfo.makeWithNext(nextFrame);
        return nextFrame;
    }

    @Override
    public Frame getLastFrame() {
        Frame lastFrame = LastFrame.init();
        frameInfo = frameInfo.makeWithNext(lastFrame);
        return lastFrame;
    }

    @Override
    public void bowling(int score) {
        if (scores.isEmpty()) {
            scores.add(ScoreInfo.firstScore(score));
            return;
        }
        ScoreInfo scoreInfo = scores.nextScore(score);
        scores.add(scoreInfo);
    }

    @Override
    public boolean isFull() {
        return (this.scores.size() == FULL_TRY) || (scores.hasStrike());
    }

    private boolean isNotFull() {
        return !isFull();
    }

    @Override
    public List<ScoreInfo> getScoreInfos() {
        return scores.getScoreInfoBundle();
    }

    @Override
    public FrameScore getFrameScore() {
        FrameScore frameScore = makeFrameScore();

        if (frameScore.canAdd()) {
            return frameInfo.calculateNext(frameScore);
        }

        return frameScore;
    }

    private FrameScore makeFrameScore() {
        if (isNotFull()) {
            return new FrameScore(scores.getSum(), 1);
        }

        Status status = scores.getStatus();
        return new FrameScore(scores.getSum(), status.getAddCount());
    }

    @Override
    public boolean isLastFrame() {
        return frameInfo.isLastNumber();
    }

    @Override
    public FrameScore addNextScore(FrameScore before) {
        FrameScore frameScore = scores.addScore(before);

        if (frameScore.canAdd()) {
            return frameInfo.calculateNext(frameScore);
        }

        return frameScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameInfo, that.frameInfo) &&
                Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameInfo, scores);
    }
}
