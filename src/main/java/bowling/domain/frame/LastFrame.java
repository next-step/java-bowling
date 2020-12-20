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
    public Frame createNextFrame(boolean isLast) {
        return init(frameNumber, score);
    }

    @Override
    public void pitch(Point point) {
        score.pitch(point);
    }

    @Override
    public boolean hasScoreTurn() {
        return score.hasScoreTurn();
    }

    @Override
    public ScoreDto getScoreDto() {
        if (!isFrameFinished()) {
            return ScoreDto.init(0, BowlType.NONE);
        }
        if (isLastFrame() && isFrameFinished()) {
            return ScoreDto.init(score.sumPoint(), BowlType.END);
        }
        int nextBowlCount = score.getBowlType().getBonusCount();
        List<Point> nextFramePitchedPoint = getNextFramePitchPoints(nextBowlCount);
        if (nextFramePitchedPoint.size() < nextBowlCount) {
            return ScoreDto.init(0, BowlType.NONE);
        }

        int sumScore = score.sumPoint() + getNextFramePitchedPoint(nextFramePitchedPoint);
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
        return !this.score.hasScoreTurn();
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
        return this.score.getPitchedPoint();
    }


    @Override
    FrameResultDto getFrameResultDto() {
        return new FrameResultDto(this.score.getPitchedPoint(), score.getBowlType(), getScoreDto());
    }
}
