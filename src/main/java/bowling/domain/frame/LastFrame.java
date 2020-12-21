package bowling.domain.frame;

import bowling.domain.BowlType;
import bowling.domain.point.Point;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreDto;

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
    public ScoreDto getScoreDto() {
        if (!isFrameFinished()) {
            return ScoreDto.init(0, BowlType.NONE);
        }
        if (isLastFrame() && isFrameFinished()) {
            return ScoreDto.init(frameBoard.sumPoint(), BowlType.END);
        }
        int nextBowlCount = frameBoard.getBowlType().getBonusCount();
        List<Point> nextFramePitchedPoint = getNextFramePitchPoints(nextBowlCount);
        if (nextFramePitchedPoint.size() < nextBowlCount) {
            return ScoreDto.init(0, BowlType.NONE);
        }

        int sumScore = frameBoard.sumPoint() + getNextFramePitchedPoint(nextFramePitchedPoint);
        return ScoreDto.init(sumScore, BowlType.END);

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
        return new FrameResultDto(frameBoard.getPitchedPoint(), frameBoard.getBowlType(), getScoreDto());
    }
}
