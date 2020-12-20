package bowling.domain.frame;

import bowling.domain.BowlType;
import bowling.domain.point.Point;
import bowling.domain.score.BasicScore;
import bowling.domain.score.LastScore;
import bowling.domain.score.Score;
import bowling.domain.score.ScoreDto;

import java.util.ArrayList;
import java.util.List;

public class BasicFrame extends Frame {


    public static final int LAST_FRAME_NUMBER = 9;

    private BasicFrame(int frameNumber, Score score) {
        super(frameNumber, score);
    }


    public static Frame initFirst() {
        return new BasicFrame(0, BasicScore.initFirst());
    }

    public static Frame init(int frameNumber, Score score) {
        return new BasicFrame(frameNumber, score);
    }


    @Override
    public Frame createNextFrame() {
        if (frameNumber == LAST_FRAME_NUMBER) {
            this.nextFrame = LastFrame.init(frameNumber + 1, LastScore.initFirst());
            return this.nextFrame;
        }

        this.nextFrame = init(frameNumber + 1, BasicScore.initFirst());
        return this.nextFrame;
    }


    @Override
    public void pitch(Point point) {
        this.score.pitch(point);
    }

    @Override
    public boolean hasScoreTurn() {
        return this.score.hasScoreTurn();
    }

    @Override
    public ScoreDto getScoreDto() {
        if (!isFrameFinished()) {
            return ScoreDto.init(0, BowlType.NONE);
        }

        if (isLastFrame() && isFrameFinished()) {
            return ScoreDto.init(score.sumPoint(), BowlType.END);
        }

        int nextBowlCount = getBonusCount();
        List<Point> pitchedPoints = getNextFramePitchPoints(nextBowlCount);

        if (pitchedPoints.size() < nextBowlCount) {
            return ScoreDto.init(0, BowlType.NONE);
        }

        int sumScore = score.sumPoint() + getNextPointSum(pitchedPoints);
        return ScoreDto.init(sumScore, BowlType.END);

    }

    private Integer getNextPointSum(List<Point> pitchedPoints) {
        return pitchedPoints.stream()
                .map(Point::getPoint)
                .reduce(0, Integer::sum);
    }

    private int getBonusCount() {
        return score.getBonusCount();
    }

    @Override
    protected boolean isLastFrame() {
        return false;
    }

    @Override
    protected boolean isFrameFinished() {
        return !this.score.hasScoreTurn();
    }

    @Override
    protected List<Point> getNextFramePitchPoints(int count) {
        if (nextFrame == null) {
            return new ArrayList<>();
        }

        List<Point> nextDownPins = nextFrame.getFramePitchPoints();
        if (nextDownPins.isEmpty()) {
            return new ArrayList<>();
        }

        if (nextDownPins.size() >= count) {
            return nextDownPins.subList(0, count);
        }

        nextDownPins.addAll(nextFrame.getNextFramePitchPoints(count - nextDownPins.size()));
        return nextDownPins;
    }

    @Override
    protected List<Point> getFramePitchPoints() {
        return score.getPitchedPoint();
    }


    @Override
    FrameResultDto getFrameResultDto() {
        return new FrameResultDto(score.getPitchedPoint(), score.getBowlType(), getScoreDto());
    }


}
