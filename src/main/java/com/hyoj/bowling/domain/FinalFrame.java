package com.hyoj.bowling.domain;

import static java.util.stream.Collectors.joining;

import com.hyoj.bowling.console.OutputConsole;
import com.hyoj.bowling.domain.status.None;
import com.hyoj.bowling.domain.status.ResultStatus;
import com.hyoj.bowling.domain.status.Strike;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private final Frame frame;
    private final List<Shot> shots = new ArrayList<>();

    public FinalFrame(DefaultFrame frame) {
        this.frame = frame;
    }

    public boolean canOneMoreShot() {
        return getResultStatus().canOneMoreShotAtFinal();
    }

    @Override
    public FinalFrame add(int knockDownPinsCount) {
        if (!frame.isDone()) {
            frame.add(knockDownPinsCount);
            return this;
        }

        final int shotTimes = shots.size();

        if (shotTimes > MAX_SHOTS_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 투구 횟수");
        }

        if (shotTimes == 0 || shots.get(shotTimes - 1).isAllDown()) {
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
            return frame.getResultStatus();
        }

        return ResultStatus.getResultStatusInstance(shots);
    }

    @Override
    public boolean isDone() {
        final int shotTimes = shots.size();

        if (shotTimes == 0) {
            return false;
        }

        return shotTimes == MAX_SHOTS_COUNT || !shots.get(shotTimes - 1).isAllDown();
    }

    @Override
    public String toString() {
        if (!frame.isDone() || shots.size() == 0) {
            return frame.toString();
        }

        return frame + OutputConsole.BAR + shots.stream()
            .map(shot -> shot.isAllDown() ? Strike.getInstance().toString() : shot.toString())
            .collect(joining(OutputConsole.BAR));
    }
}
