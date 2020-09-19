package bowling.model.frame;

import bowling.model.Result;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame extends Frame {
    public static final int MAX_FRAME_INDEX = 10;

    protected NormalFrame(int index, List<Result> results) {
        super(index, results);
    }

    public static NormalFrame start() {
        return new NormalFrame(1, new ArrayList<>());
    }

    @Override
    public Frame next() {
        if (index == MAX_FRAME_INDEX) {
            throw new RuntimeException("마지막 프레임입니다.");
        }
        if (index == MAX_FRAME_INDEX - 1) {
            return new FinalFrame();
        }
        return new NormalFrame(index + 1, new ArrayList<>());
    }

    @Override
    public boolean isEnded() {
        return hasStrike() || results.size() == 2;
    }
}
