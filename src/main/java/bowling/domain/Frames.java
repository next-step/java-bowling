package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int END_FRAME_INDEX = 9;
    private int currentFrameIndex = 0;
    private final List<Frame> values;

    private Frames(List<Frame> values) {
        this.values = values;
    }

    public static Frames init() {
        List<Frame> values = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            values.add(Frame.createNormal());
        }

        values.add(Frame.createFinal());
        return new Frames(values);
    }

    public void bowling(int count) {
        Frame frame = getFrame();
        frame.bowling(count);
    }

    public boolean canBowling() {
        return !endGame();
    }

    private boolean endGame() {
        return currentFrameIndex == END_FRAME_INDEX
                && values.get(END_FRAME_INDEX).isEnd();
    }

    private Frame getFrame() {
        Frame current = values.get(currentFrameIndex);

        if (!current.isEnd()) {
            return current;
        }

        currentFrameIndex++;
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
