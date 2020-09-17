package bowling.domain.frame;

import bowling.domain.pin.Pin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ScoreBoard {

    public static final int END_FRAME_COUNT = 10;
    private static final int NORMAL_FRAME_COUNT = 9;
    private final List<Frame> frames;

    private ScoreBoard(List<Frame> frames) {
        this.frames = frames;
    }

    public static ScoreBoard init() {
        return new ScoreBoard(new LinkedList<>(Arrays.asList(Frame.normalFrame())));
    }

    public void bowl(Pin felledPin) {
        Frame current = frames.get(frames.size() - 1);
        current.bowl(felledPin);
        if (current.isEnd() && !isGameOver()) {
            generateFrame();
        }
    }

    private void generateFrame() {
        if (frames.size() < NORMAL_FRAME_COUNT) {
            frames.add(Frame.normalFrame());
            return;
        }
        frames.add(Frame.endFrame());
    }

    public int size() {
        return frames.size();
    }

    public boolean isGameOver() {
        return frames.size() == END_FRAME_COUNT && frames.get(frames.size() - 1).isEnd();
    }

    public List<Frame> getFrames() {
        return this.frames;
    }
}
