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
        this.frames.add(new Frame());
    }

    public List<List<ResultType>> getGameResult() {
        return frames.stream().map(Frame::getResult).collect(Collectors.toList());
    }

    public int getCurrentFrame() {
        return frames.size();
    }

    public boolean bowling(int pin) {
        boolean isFrameFinish = frames.getLast().addScore(pin);
        if (isFrameFinish && isNotFinalFrame()) {
            frames.add(new Frame());
        }
        return isFrameFinish;
    }

    private boolean isNotFinalFrame() {
        return frames.size() != finalFrame;
    }
}
