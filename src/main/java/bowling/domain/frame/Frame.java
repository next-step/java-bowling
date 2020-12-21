package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.LastScore;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreDto;

import java.util.List;
import java.util.Objects;

public abstract class Frame {

    protected FrameBoard frameBoard;
    protected Frame nextFrame;

    protected Frame(int frameNumber, Score score) {
        this.frameBoard = FrameBoard.of(frameNumber , score);
    }

    public Frame createLastFrame() {
        this.nextFrame = LastFrame.init(frameBoard.increaseFrameNumber(), LastScore.initFirst());
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
        return Objects.equals(frameBoard, frame.frameBoard) && Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameBoard, nextFrame);
    }
}
