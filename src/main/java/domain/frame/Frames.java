package domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    public static int START_FRAME = 1;
    public static int LAST_FRAME = 10;

    private final List<Frame> frames = new ArrayList<>();

    public boolean isEmpty() {
        return frames.isEmpty();
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public int getNextFrameNumber() {
        if(isEmpty()) {
            return START_FRAME;
        }

        if(getRecentFrame().isFinished()) {
            return frames.size()+1;
        }

        return frames.size();
    }

    public Frame getRecentFrame() {
        return frames.get(frames.size() - 1);
    }

    public boolean contains(Frame frame) {
        return frames.contains(frame);
    }

    public String getStatus() {
        return frames.stream()
                    .map(Frame::toString)
                    .collect(Collectors.joining("|"));
    }
}