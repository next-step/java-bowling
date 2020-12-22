package bowling.domain.frame;

import bowling.domain.score.ScoreType;
import bowling.domain.point.Point;
import bowling.domain.score.Score;

import java.util.List;

public class FrameBoard {
    private final int frameNumber;
    private final Score score;

    private FrameBoard(int frameNumber, Score score) {
        this.frameNumber = frameNumber;
        this.score = score;
    }


    public static FrameBoard of(int frameNumber, Score score) {
        return new FrameBoard(frameNumber, score);
    }

    public int increaseFrameNumber() {
        return this.frameNumber + 1;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public Score getScore() {
        return score;
    }

    public void pitch(Point point) {
        score.pitch(point);
    }

    public boolean hasScoreTurn() {
        return score.hasScoreTurn();
    }

    public int sumPoint() {
        return score.sumPoint();
    }

    public int getBonusCount() {
        return score.getBonusCount();
    }

    public List<Point> getPitchedPoint() {
        return score.getPitchedPoint();
    }

    public ScoreType getBowlType() {
        return score.getBowlType();
    }
}
