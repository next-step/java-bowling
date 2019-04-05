package domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    public static int START_FRAME = 1;
    public static int LAST_FRAME = 10;

    private List<Frame> frames = new ArrayList<>();

    public boolean isEmpty() {
        return frames.isEmpty();
    }

    public Frames add(Frame frame) {
        frames.add(frame);
        return this;
    }

    public Frame getRecentFrame() {
        return frames.get(frames.size() - 1);
    }

    public int getRecentFrameNumber() {
        return frames.get(frames.size() - 1).getNumber();
    }

    public int size() {
        return frames.size();
    }

    public boolean contains(Frame frame) {
        return frames.contains(frame);
    }

    public String getStatus() {
        return frames.stream()
                    .map(Frame::toString)
                    .collect(Collectors.joining());
    }
}