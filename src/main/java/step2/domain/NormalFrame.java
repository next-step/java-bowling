package step2.domain;

import step2.strategy.PitchesStrategy;
import step2.type.ResultPitchesType;

import java.util.Objects;

import static java.util.Objects.nonNull;
import static step2.domain.BowlingPoint.NO_MARK;
import static step2.domain.BowlingPoint.STRIKE_MARK;
import static step2.type.ResultPitchesType.*;

public class NormalFrame implements Frame {
    public static final String ERROR_INVALID_SHOT_POINT = "투구 값이 유효하지 않습니다.";

    private final int frameNo;
    private BowlingPoint firstPoint;
    private BowlingPoint secondPoint;
    private boolean completed;
    private final Frame next;

    public NormalFrame(Builder builder) {
        this.frameNo = builder.frameNo;
        this.firstPoint = builder.firstPoint;
        this.secondPoint = builder.secondPoint;
        this.completed = builder.completed;
        this.next = builder.next;
    }

    public static NormalFrame of(int frameNo) {
        return Builder(frameNo, makeNext(frameNo))
                .completed(false)
                .build();
    }

    public static NormalFrame of(int frameNo, Frame next) {
        return Builder(frameNo, next)
                .completed(false)
                .build();
    }

    public static Builder Builder(int frameNo, Frame next) {
        return new Builder(frameNo, next);
    }

    public static class Builder {
        private final int frameNo;
        private BowlingPoint firstPoint;
        private BowlingPoint secondPoint;
        private boolean completed;
        private final Frame next;

        public Builder(int frameNo, Frame next) {
            this.frameNo = frameNo;
            this.next = next;
        }

        public Builder firstPoint(BowlingPoint firstPoint) {
            this.firstPoint = firstPoint;
            return this;
        }

        public Builder secondPoint(BowlingPoint secondPoint) {
            this.secondPoint = secondPoint;
            return this;
        }

        public Builder completed(boolean completed) {
            this.completed = completed;
            return this;
        }

        public NormalFrame build() {
            return new NormalFrame(this);
        }

    }

    @Override
    public int pitches(PitchesStrategy strategy) {
        if (Objects.isNull(firstPoint)) {
            firstPoint = BowlingPoint.of(strategy.shot(0));
            return firstPoint.getPoint();
        }

        if (Objects.isNull(secondPoint)) {
            secondPoint = BowlingPoint.of(strategy.shot(firstPoint.getPoint()), firstPoint.getPoint());
            return secondPoint.getPoint();
        }
        completed = true;
        return next.pitches(strategy);
    }

    @Override
    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public int getScore() {
        int score = getCurrentScore();
        ResultPitchesType type = getCurrentType();
        if (STRIKE.equals(type) || SPARE.equals(type)) {
            score += next.getScore(type);
        }
        return score;
    }

    @Override
    public int getScore(ResultPitchesType prevType) {
        ResultPitchesType currentType = getCurrentType();
        if (STRIKE.equals(prevType) && STRIKE.equals(currentType)) {
            return getCurrentScore() + next.getCurrentScore();
        }
        if (STRIKE.equals(prevType)) {
            return getCurrentScore();
        }
        if (SPARE.equals(prevType)) {
            return getScoreByPoint(firstPoint);
        }
        return 0;
    }

    private ResultPitchesType getCurrentType() {
        return getType(getScoreByPoint(firstPoint), getScoreByPoint(secondPoint));
    }

    private int getScoreByPoint(BowlingPoint point) {
        return Objects.nonNull(point) ? point.getPoint() : 0;
    }


    @Override
    public int getCurrentScore() {
        return getScoreByPoint(firstPoint) + getScoreByPoint(secondPoint);
    }


    @Override
    public Frame next() {
        return next;
    }

    @Override
    public boolean hasNext() {
        return nonNull(next);
    }

    public static Frame makeNext(int frameNo) {
        if (frameNo == BowlingGame.FRAME_LAST_NO) {
            return new FinalFrame(frameNo + 1);
        }
        return NormalFrame.of(frameNo + 1);
    }

    @Override
    public String getResultString() {
        String result = nonNull(firstPoint) ? firstPoint.getMark() : NO_MARK;
        result += nonNull(secondPoint) && !result.equals(STRIKE_MARK) ? "|" + secondPoint.getMark() : NO_MARK;

        return result;
    }

    @Override
    public boolean isFinished() {
        return completed;
    }
}
