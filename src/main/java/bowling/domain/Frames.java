package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private final int finalFrame;
    private final LinkedList<Frame> frames;

    public Frames(int finalFrame) {
        this.finalFrame = finalFrame;
        this.frames = new LinkedList<>();
        this.frames.add(new NormalFrame());
    }

    public List<List<Shot>> getGameResult() {
        return frames.stream().map(Frame::getResult).collect(Collectors.toList());
    }

    public int getCurrentFrame() {
        return frames.size();
    }

    public boolean bowling(int pin) {
        boolean isFrameFinish = frames.getLast().bowling(pin);
        if (isFrameFinish) {
            createNextFrame();
        }
        return isFrameFinish;
    }

    private boolean isNextFrameIsFinal() {
        return frames.size() == finalFrame - 1;
    }

    private boolean isGameNotFinish() {
        return frames.size() != finalFrame;
    }

    private void createNextFrame() {
        if (isNextFrameIsFinal()) {
            frames.add(new FinalFrame());
            return;
        }
        if (isGameNotFinish()) {
            frames.add(new NormalFrame());
        }
    }
}
