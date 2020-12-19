package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreDto;

import java.util.List;

public abstract class Frame {

    protected int frameNumber;
    protected final Score score;
    protected Frame nextFrame;

    protected Frame(int frameNumber, Score score) {
        this.frameNumber = frameNumber;
        this.score = score;
    }

    public Frame getNextFrame() {
        return nextFrame;
    }

    abstract public Frame createNextFrame(boolean isLast);

    abstract public void pitch(Point point);

    abstract public boolean hasScoreTurn();

    abstract public ScoreDto getScoreDto();

    abstract protected boolean isLastFrame();

    abstract protected boolean isFrameFinished();

    abstract protected List<Point> getPitchPoints(int count);

    abstract protected List<Point> getPitchPoints();

    abstract FrameResultDto getFrameResultDto();
}
