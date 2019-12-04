package frame;

import frame.info.FrameNumber;
import score.ScoreInfo;
import score.ScoreInfoBundle;
import score.framescore.FrameScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static frame.info.FrameNumber.LAST_FRAME_NUMBER;

public class LastFrame implements Frame {

    private final FrameNumber frameNumber;
    private final ScoreInfoBundle scores;

    private LastFrame(int frameNumber, List<ScoreInfo> scores) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.scores = new ScoreInfoBundle(scores);
    }

    public static LastFrame init() {
        return new LastFrame(LAST_FRAME_NUMBER, new ArrayList<>());
    }

    public boolean isNotFull() {
        return !isFull();
    }

    @Override
    public Frame nextFrame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bowling(int score) {
        if (scores.isEmpty()) {
            scores.addOnLast(ScoreInfo.firstScore(score));
            return;
        }
        if (scores.isStrikeOrSpareOfLast()) {
            scores.addOnLast(ScoreInfo.firstScore(score));
            return;
        }
        ScoreInfo scoreInfo = scores.nextScore(score);
        scores.addOnLast(scoreInfo);
    }

    @Override
    public boolean isFull() {
        if (scores.size() == 3) {
            return true;
        }

        if (scores.size() < 2) {
            return false;
        }

        if (scores.hasStrike()) {
            return false;
        }

        return scores.isNormalOfLast();
    }

    @Override
    public List<ScoreInfo> getScoreInfos() {
        return scores.getScoreInfoBundle();
    }

    @Override
    public FrameScore getFrameScore() {
        int sum = scores.getSum();
        if (scores.isEmpty()) {
            return new FrameScore(0, 1);
        }
        if (scores.hasOne()) {
            return new FrameScore(sum, 1);
        }
        if (scores.hasTwo()) {
            return getSecondScore(sum);
        }
        return new FrameScore(sum, 0);
    }

    private FrameScore getSecondScore(int sum) {
        if (sum == 10) {
            return new FrameScore(sum, 1);
        }
        return new FrameScore(sum, 0);
    }

    @Override
    public FrameScore addNextScore(FrameScore before) {
        return scores.addScore(before);
    }

    @Override
    public Frame getLastFrame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastFrame lastFrame = (LastFrame) o;
        return Objects.equals(frameNumber, lastFrame.frameNumber) &&
                Objects.equals(scores, lastFrame.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, scores);
    }
}
