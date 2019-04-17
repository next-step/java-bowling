package com.hyoj.bowling.domain;

import com.hyoj.bowling.domain.status.None;
import com.hyoj.bowling.domain.status.ResultStatus;
import com.hyoj.bowling.domain.status.Strike;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class DefaultFrame implements Frame {
    private final List<Shot> shots = new ArrayList<>();
    private final int index;

    private DefaultFrame(int index) {
        this.index = index;
    }

    public static Frame createFirstFrame() {
        return new DefaultFrame(1);
    }

    public Frame createNextFrame() {
        final int indexPlusOne = this.index + 1;
        if (indexPlusOne == GameBoard.MAX_FRAMES_COUNT) {
            return createFinalFrame();
        }

        return new DefaultFrame(indexPlusOne);
    }

    public Frame createFinalFrame() {
        return new FinalFrame(new DefaultFrame(GameBoard.MAX_FRAMES_COUNT));
    }

    @Override
    public DefaultFrame add(int knockDownPinsCount) {
        final int shotTimes = shots.size();

        if (shotTimes > MAX_SHOTS_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 투구 횟수");
        }

        if (shotTimes == 0) {
            shots.add(new Shot(knockDownPinsCount));
            return this;
        }

        shots.add(new Shot(knockDownPinsCount, shots.get(shotTimes - 1)));
        return this;
    }

    @Override
    public ResultStatus getResultStatus() {
        final int shotTimes = shots.size();
        if (shotTimes == 0) {
            return None.getInstance();
        }

        return ResultStatus.getResultStatusInstance(shots);
    }

    @Override
    public boolean isDone() {
        final int shotsCount = shots.size();

        if (shotsCount == 0) {
            return false;
        }

        return shotsCount == MAX_SHOTS_COUNT || shots.get(shotsCount - 1).isAllDown();
    }

    @Override
    public String toString() {
        final ResultStatus resultStatus = ResultStatus.getResultStatusInstance(shots);

        if (resultStatus == Strike.getInstance()) {
            return resultStatus.toString();
        }

        return shots.stream()
            .map(Shot::toString)
            .collect(joining("|"));
    }
}