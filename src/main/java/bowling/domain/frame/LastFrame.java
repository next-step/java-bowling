package bowling.domain.frame;

import bowling.domain.score.ScoreType;
import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastFrame extends Frame {


    protected LastFrame(int frameNumber, Score score) {
        super(frameNumber, score);
    }

    public static Frame init(int frameNumber, Score score) {
        return new LastFrame(frameNumber, score);
    }


    @Override
    public Frame createNextFrame() {
        return init(frameBoard.getFrameNumber(), frameBoard.getScore());
    }

    @Override
    public void pitch(Point point) {
        frameBoard.pitch(point);
    }

    @Override
    public boolean hasScoreTurn() {
        return frameBoard.hasScoreTurn();
    }

    @Override
    public ScoreResult getScoreResult() {
        if (!isFrameFinished()) {
            return ScoreResult.initFinished();
        }

        if (isLastFrame() && isFrameFinished()) {
            return ScoreResult.initLastFrameFinished(frameBoard.sumPoint());
        }

        int nextBowlCount = getBonusCount();
        List<Point> pitchedPoints = getNextFramePitchPoints(nextBowlCount);

        if (pitchedPoints.size() < nextBowlCount) {
            return ScoreResult.initFinished();
        }


        return ScoreResult.init(frameBoard.sumPoint() + getNextFramePitchedPoint(pitchedPoints), ScoreType.END);
    }

    private int getBonusCount() {
        return frameBoard.getBonusCount();
    }

    private Integer getNextFramePitchedPoint(List<Point> nextFramePitchedPoint) {
        return nextFramePitchedPoint.stream()
                .map(Point::getPoint)
                .reduce(0, Integer::sum);
    }

    @Override
    protected boolean isLastFrame() {
        return true;
    }

    @Override
    protected boolean isFrameFinished() {
        return !frameBoard.hasScoreTurn();
    }

    @Override
    protected List<Point> getNextFramePitchPoints(int count) {
        if (nextFrame == null) {
            return Collections.emptyList();
        }

        List<Point> nextDownPins = nextFrame.getFramePitchPoints();
        if (nextDownPins.isEmpty()) {
            return new ArrayList<>();
        }

        if (nextDownPins.size() >= count) {
            return nextDownPins.subList(0, count);
        }

        nextDownPins.addAll(this.nextFrame.getNextFramePitchPoints(count - nextDownPins.size()));
        return nextDownPins;
    }

    @Override
    protected List<Point> getFramePitchPoints() {
        return frameBoard.getPitchedPoint();
    }


    @Override
    FrameResultDto getFrameResultDto() {
        return new FrameResultDto(frameBoard.getPitchedPoint(), frameBoard.getBowlType(), getScoreResult());
    }
}
