package com.hyoj.bowling.domain;

import static java.util.stream.Collectors.joining;

import com.hyoj.bowling.console.OutputConsole;
import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    public static final int MAX_SHOTS_COUNT = 3;

    private final Frame frame;
    private final List<Shot> shots = new ArrayList<>();

    public FinalFrame(DefaultFrame frame) {
        this.frame = frame;
    }

    public boolean canOneMoreShot() {
        int shotTimes = shots.size();
        if (shotTimes == 0 && frame.getFinalMarkType().canOneMoreShotAtFinal()) {
            return true;
        }

        if (shots.get(shotTimes - 1).isAllDown()) {
            return true;
        }

        return false;
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
    public boolean isDone() {
        final int shotTimes = shots.size();

        if (shotTimes == 0) {
            return false;
        }

        return shotTimes == MAX_SHOTS_COUNT || !shots.get(shotTimes - 1).isAllDown();

    }

    @Override
    public MarkType getFinalMarkType() {
        final int shotTimes = shots.size();
        if (shotTimes == 0) {
            return MarkType.NONE;
        }

        if (isDone()) {
            return MarkType.STRIKE;
        }

        return MarkType.makeMarkType(shotTimes, shots.get(shotTimes - 1));
    }

    @Override
    public String toString() {
        if (!frame.isDone() || shots.size() == 0) {
            return frame.toString();
        }

        return frame + OutputConsole.BAR + shots.stream()
            .map(shot -> shot.isAllDown() ? MarkType.STRIKE.toString() : shot.toString())
            .collect(joining(OutputConsole.BAR));
    }
}
