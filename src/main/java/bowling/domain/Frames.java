package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int END_FRAME_INDEX = 10;
    private int currentFrameIndex = 0;
    private final List<Frame> values;

    private Frames(List<Frame> values) {
        this.values = values;
    }

    public static Frames init() {
        List<Frame> values = new ArrayList<>();

        Frame frame = Frame.createFirst();
        values.add(frame);

        for (int i = 0; i < 9; i++) {
            frame = frame.createNext();
            values.add(frame);
        }

        values.add(frame.createFinal());
        return new Frames(values);
    }

    public void bowling(int count) {
        Frame frame = getCurrentFrame();
        frame.bowling(count);

        if (frame.isEnd()) {
            currentFrameIndex++;
        }
    }

    public boolean canBowling() {
        return !endGame();
    }

    private boolean endGame() {
        return currentFrameIndex == END_FRAME_INDEX;
    }

    private Frame getCurrentFrame() {
        return values.get(currentFrameIndex);
    }

    public Frame get(int index) {
        return values.get(index);
    }

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public int size() {
        return values.size();
    }
}
