package bowling.model.frame;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class Frames {
    private final Set<Frame> frames = new TreeSet<>();

    public void bowling(int fallenPins) {
        if (frames.isEmpty()) {
            Frame firstFrame = NormalFrame.createFirstFrame();
            frames.add(firstFrame);
        }

        Frame nowFrame = lastFrame();
        frames.add(nowFrame.bowling(fallenPins));
    }

    public int nowFrameNumber() {
        if (frames.isEmpty()) {
            return 1;
        }

        int frameNumber = Integer.parseInt(lastFrame().frameNumber.toString());

        if (isFinished()) {
            return frameNumber + 1;
        }

        return frameNumber;
    }

    public boolean isFinished() {
        if (frames.isEmpty()) {
            return false;
        }

        return lastFrame().isFinished();
    }

    private Frame lastFrame() {
        return ((TreeSet<Frame>) frames).last();
    }

    public FrameResult result() {
        List<String> results = frames.stream()
                .filter(this::isNotStartState)
                .map(Frame::toString)
                .collect(Collectors.toList());

        return FrameResult.from(results);
    }

    private boolean isNotStartState(Frame frame) {
        return !frame.isStartFrame();
    }
}
