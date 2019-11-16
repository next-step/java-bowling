package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    public static final int LAST_FRAME = 10;
    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public Frame nextFrame() {
        return frames.stream()
                .filter(Frame::isAddAble)
                .findFirst()
                .orElse(createFrame());
    }

    private Frame createFrame() {
        Frame frame = new Frame(frames.size() + 1);
        frames.add(frame);
        return frame;
    }

    public List<String> getResult() {
        return frames.stream()
                .map(Frame::getResult)
                .collect(Collectors.toList());
    }
}
