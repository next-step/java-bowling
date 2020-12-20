package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.LastScore;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreDto;

import java.util.List;
import java.util.Objects;

public abstract class Frame {

    protected int frameNumber;
    protected final Score score;
    protected Frame nextFrame;

    protected Frame(int frameNumber, Score score) {
        this.frameNumber = frameNumber;
        this.score = score;
    }

    public Frame createLastFrame() {
        this.nextFrame = LastFrame.init(this.frameNumber + 1, LastScore.initFirst());
        return this.nextFrame;
    }

    abstract public Frame createNextFrame();

    abstract public void pitch(Point point);

    abstract public boolean hasScoreTurn();

    abstract public ScoreDto getScoreDto();

    abstract protected boolean isLastFrame();

    abstract protected boolean isFrameFinished();

    abstract protected List<Point> getNextFramePitchPoints(int count);

    abstract protected List<Point> getFramePitchPoints();

    abstract FrameResultDto getFrameResultDto();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return frameNumber == frame.frameNumber && Objects.equals(score, frame.score) && Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, score, nextFrame);
    }


}
