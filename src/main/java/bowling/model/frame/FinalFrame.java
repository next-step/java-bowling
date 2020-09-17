package bowling.model.frame;

import bowling.model.Result;

import java.util.ArrayList;

public class FinalFrame extends Frame {
    public FinalFrame() {
        super(Frame.MAX_FRAME_INDEX, new ArrayList<>());
    }

    @Override
    public void addResult(int count) {
        if (isEnded()) {
            throw new IllegalStateException("이미 끝난 프레임입니다.");
        }
        if (!hasStrike() && !hasSpare()) {
            super.addResult(count);
            return;
        }
        results.add(Result.of(count));
    }

    @Override
    public Frame next() {
        throw new RuntimeException("마지막 프레임입니다.");
    }

    @Override
    public boolean isEnded() {
        if (hasSpare() || hasStrike()) {
            return results.size() == 3;
        }
        return results.size() == 2;
    }
}
