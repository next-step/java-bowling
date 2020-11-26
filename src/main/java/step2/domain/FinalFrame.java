package step2.domain;

import step2.exception.NotExistsNextFrameException;
import step2.exception.NotFoundPitchesTypeException;
import step2.strategy.PitchesStrategy;
import step2.type.ResultPitchesType;

import static step2.type.ResultPitchesType.MISS;
import static step2.type.ResultPitchesType.SPARE;

public class FinalFrame implements Frame {
    public static final String ERROR_CURRENT_FRAME_IS_FINAL = "해당 프레임이 마지막 프레임입니다.";
    public static final String ERROR_NOT_REMAIING_PITCHIES_COUNT = "더 이상 투구할 수 없습니다.";
    public static final String STRIKE_STR = "X";
    public static final String SPARE_STR = "/";
    public static final String GUTTER_STR = "-";

    private final int frameNo;
    private int remainingCount;
    private boolean completed;
    private int firstPoint;
    private int secondPoint;
    private int thirdPoint;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.firstPoint = 0;
        this.secondPoint = 0;
        this.thirdPoint = 0;
        this.remainingCount = 3;
        this.completed = false;
    }

    @Override
    public int pitches(PitchesStrategy strategy) {
        if (remainingCount == 3) {
            remainingCount--;
            firstPoint = strategy.shot(0);
            return firstPoint;
        }
        if (remainingCount == 2) {
            remainingCount--;
            secondPoint = strategy.shot(firstPoint == 10 ? 0 : firstPoint);
            updateComplete();
            return secondPoint;
        }
        if (getCurrentScore() == 10 && remainingCount == 1) {
            remainingCount--;
            thirdPoint = strategy.shot(secondPoint == 10 ? 0 : secondPoint);
            updateComplete();
            return thirdPoint;
        }

        throw new IllegalArgumentException();
    }

    private void updateComplete() {
        if (firstPoint + secondPoint < 10 || remainingCount == 0) {
            completed = true;
        }
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public int getScore() {
        return getCurrentScore();
    }

    @Override
    public int getCurrentScore() {
        return firstPoint + secondPoint + thirdPoint;
    }

    @Override
    public ResultPitchesType getPitchesType() {
        return ResultPitchesType.getType(firstPoint, secondPoint);
    }

    @Override
    public Frame next() {
        throw new NotExistsNextFrameException(ERROR_CURRENT_FRAME_IS_FINAL);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Frame makeNext() {
        return null;
    }

    @Override
    public String getResultString() {
        ResultPitchesType type = getPitchesType();
        if (isStrike(type) && !completed) {
            return STRIKE_STR;
        }

        if (isSpare(type) && !completed) {
            return String.format("%s|%s", checkGutter(firstPoint), SPARE_STR);
        }

        if (isMiss(type) && !completed) {
            return String.format("%s|%s", checkGutter(firstPoint), checkGutter(secondPoint));
        }

        


    }

    private String checkGutter(int point) {
        return point == 0 ? GUTTER_STR : String.valueOf(point);
    }

    private boolean isStrike(ResultPitchesType pitchesType) {
        return ResultPitchesType.STRIKE.equals(pitchesType);
    }

    private boolean isSpare(ResultPitchesType pitchesType) {
        return SPARE.equals(pitchesType) && remainingCount == 0;
    }

    private boolean isMiss(ResultPitchesType pitchesType) {
        return MISS.equals(pitchesType) && remainingCount == 0;
    }

    @Override
    public boolean isFinished() {
        return completed;
    }

    @Override
    public int getFirstScore() {
        return firstPoint;
    }
}
