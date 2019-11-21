package frame;

import score.ScoreInfo;
import score.ScoreInfoBundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NormalFrame implements Frame {
    public static final int FIRST_FRAME_NUMBER = 1;

    private static final int FULL_TRY = 2;

    private final FrameNumber frameNumber;
    private final ScoreInfoBundle scores;

    NormalFrame(int frameNumber, List<ScoreInfo> scores) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.scores = new ScoreInfoBundle(scores);
    }

    public static NormalFrame firstNormalFrame() {
        return new NormalFrame(FIRST_FRAME_NUMBER, new ArrayList<>());
    }

    @Override
    public Frame nextFrame() {
        return new NormalFrame(frameNumber.next(), new ArrayList<>());
    }

    @Override
    public void bowling(int score) {
        if (scores.size() == 0) {
            scores.add(ScoreInfo.firstScore(score));
            return;
        }
        ScoreInfo scoreInfo = scores.getFirst()
                .nextScore(score);
        scores.add(scoreInfo);
    }

    @Override
    public boolean isFull() {
        return (this.scores.size() == FULL_TRY) || (scores.hasStrike());
    }

    @Override
    public List<ScoreInfo> getScoreInfos() {
        return scores.getScoreInfoBundle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, scores);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "frameNumber=" + frameNumber +
                ", scores=" + scores +
                '}';
    }
}
