package bowling.model.frame;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class Frames {
    private final SortedSet<Frame> frames = new TreeSet<>();

    public Frames(){
        Frame firstFrame = NormalFrame.createFirstFrame();
        frames.add(firstFrame);
    }

    public void bowling(int fallenPins) {
        Frame nowFrame = lastFrame();
        frames.add(nowFrame.bowling(fallenPins));
    }

    public int nowFrameNumber() {
        int frameNumber = Integer.parseInt(lastFrame().frameNumber.toString());

        if (isFinished()) {
            return frameNumber + 1;
        }

        return frameNumber;
    }

    public boolean isFinished() {
        return lastFrame().isFinished();
    }

    private Frame lastFrame() {
        return frames.last();
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
