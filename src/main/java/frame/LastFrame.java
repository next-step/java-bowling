package frame;

import frame.info.FrameNumber;
import score.ScoreInfo;
import score.ScoreInfoBundle;
import score.framescore.FrameScore;
import score.framescore.LastFrameScores;

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

    @Override
    public Frame nextFrame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void bowling(int score) {
        if (scores.size() == 0) {
            scores.addOnLast(ScoreInfo.firstScore(score));
            return;
        }
        if (scores.isStrikeOrSpareOfLast()) {
            scores.addOnLast(ScoreInfo.firstScore(score));
            return;
        }
        ScoreInfo scoreInfo = scores.getLast()
                .nextScore(score);
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

    public boolean isNotFull() {
        return !isFull();
    }

    @Override
    public List<ScoreInfo> getScoreInfos() {
        return scores.getScoreInfoBundle();
    }

    @Override
    public FrameScore getFrameScore() {
        LastFrameScores lastFrameScores = new LastFrameScores();
        lastFrameScores.addFirst(scores);
        lastFrameScores.addSecond(scores);
        lastFrameScores.addThird(scores);

        return lastFrameScores.sum();
    }

    @Override
    public FrameScore addNextScore(FrameScore before) {
        return scores.addScore(before);
    }

    @Override
    public int getFrameNumber() {
        return frameNumber.getNumber();
    }

    @Override
    public Frame getLastFrame() {
        throw new UnsupportedOperationException();
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
