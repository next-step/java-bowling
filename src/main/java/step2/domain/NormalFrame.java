package step2.domain;

import step2.exception.NotFoundPitchesTypeException;
import step2.strategy.PitchesStrategy;
import step2.type.ResultPitchesType;

import java.util.Objects;

import static step2.type.ResultPitchesType.*;

public class NormalFrame implements Frame {
    public static final String ERROR_INVALID_SHOT_POINT = "투구 값이 유효하지 않습니다.";
    public static final String resultForm = "%s|%s";
    public static final String STRIKE_STR = "X";
    public static final String SPARE_STR = "/";
    public static final String GUTTER_STR = "-";

    private final int frameNo;
    private int remainingCount;
    private boolean completed;
    private int firstPoint;
    private int secondPoint;

    private final Frame next;

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        firstPoint = 0;
        secondPoint = 0;
        remainingCount = 2;
        completed = false;

        this.next = makeNext();
    }

    public NormalFrame(int frameNo, int remainingCount, int firstPoint, int secondPoint, Frame next) {
        this.frameNo = frameNo;
        this.remainingCount = remainingCount;
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.next = next;
    }

    @Override
    public int pitches(PitchesStrategy strategy) {
        if (remainingCount == 2) {
            remainingCount--;
            firstPoint = checkPoint(secondPoint, strategy.shot(0));
            return firstPoint;
        }

        if (remainingCount == 1 && firstPoint != 10) {
            remainingCount--;
            secondPoint = checkPoint(firstPoint, strategy.shot(firstPoint));
            return secondPoint;
        }
        completed = true;
        return next.pitches(strategy);
    }

    private int checkPoint(int sourcePoint, int shotPoint) {
        if (sourcePoint + shotPoint > 10) {
            throw new IllegalArgumentException(ERROR_INVALID_SHOT_POINT);
        }
        return shotPoint;
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public int getScore() {
        int score = getCurrentScore();
        ResultPitchesType pitchesType = getPitchesType();

        if (isDouble(pitchesType)) {
            return score + next.getCurrentScore() + next.next().getFirstScore();
        }

        if (isStrike(pitchesType)) {
            return score + next.getCurrentScore();
        }

        if (isSpare(pitchesType)) {
            return score + next.getFirstScore();
        }

        return score;
    }

    public int getFirstScore() {
        return firstPoint;
    }

    private boolean isDouble(ResultPitchesType pitchesType) {
        return ResultPitchesType.STRIKE.equals(pitchesType) && ResultPitchesType.STRIKE.equals(next.getPitchesType());
    }

    private boolean isStrike(ResultPitchesType pitchesType) {
        return ResultPitchesType.STRIKE.equals(pitchesType) && !ResultPitchesType.STRIKE.equals(next.getPitchesType());
    }

    private boolean isSpare(ResultPitchesType pitchesType) {
        return SPARE.equals(pitchesType)&&remainingCount == 0;
    }

    private boolean isMiss(ResultPitchesType pitchesType) {
        return MISS.equals(pitchesType)&&remainingCount == 0;
    }

    @Override
    public int getCurrentScore() {
        return firstPoint + secondPoint;
    }

    @Override
    public ResultPitchesType getPitchesType() {
        return ResultPitchesType.getType(firstPoint, secondPoint);
    }


    @Override
    public Frame next() {
        return next;
    }

    @Override
    public boolean hasNext() {
        return Objects.nonNull(next);
    }

    @Override
    public Frame makeNext() {
        if (frameNo == BowlingGame.FRAME_LAST_NO) {
            return new FinalFrame(frameNo + 1);
        }
        return new NormalFrame(frameNo + 1);
    }

    @Override
    public String getResultString() {
        ResultPitchesType type = getPitchesType();
        if (isStrike(type)) {
            return STRIKE_STR;
        }

        if (isSpare(type)) {
            return String.format(resultForm, checkGutter(firstPoint), SPARE_STR);
        }

        if (isMiss(type)) {
            return String.format(resultForm, checkGutter(firstPoint), checkGutter(secondPoint));
        }

        if (remainingCount == 2) {
            return "";
        }

        if (remainingCount == 1) {
            return String.valueOf(firstPoint);
        }

        throw new NotFoundPitchesTypeException();
    }

    @Override
    public boolean isFinished() {
        return completed;
    }

    private String checkGutter(int point) {
        return point == 0 ? GUTTER_STR : String.valueOf(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNo == that.frameNo &&
                remainingCount == that.remainingCount &&
                firstPoint == that.firstPoint &&
                secondPoint == that.secondPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, remainingCount, firstPoint, secondPoint);
    }
}
