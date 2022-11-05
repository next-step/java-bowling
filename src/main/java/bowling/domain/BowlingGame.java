package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class BowlingGame {
    public static final int START_FRAME = 1;
    public static final int LAST_FRAME = 10;

    private final List<Frame> frames;

    public BowlingGame() {
        frames = new ArrayList<>(List.of(new NormalFrame(START_FRAME)));
    }

    public void bowl(int falledPins) {
        getCurrentFrame().bowl(falledPins);

        if (isCurrentFrameEnded() && isGamePlayable()) {
            frames.add(getCurrentFrame().createNextFrame());
        }
    }

    public boolean isGamePlayable() {
        return !(frames.size() == LAST_FRAME && isCurrentFrameEnded());
    }

    public int getCurrentFrameNumber() {
        return getCurrentFrame().getFrameNumber();
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    private boolean isCurrentFrameEnded() {
        return getCurrentFrame().isFinish();
    }
}
