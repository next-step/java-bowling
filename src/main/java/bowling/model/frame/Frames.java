package bowling.model.frame;

import java.util.LinkedList;
import java.util.stream.Stream;

public class Frames {
    public static final int TOTAL_FRAME_COUNT = 10;

    private LinkedList<Frame> frames;

    public Frames() {
        frames = new LinkedList<>();
    }

    public int playBowling(int frameNo, int pins) {
        if (remainDelivery(frameNo)) {
            Frame now = frames.pollLast();
            frames.add(now.roll(pins));

            return nextPlayFrameNo(frameNo);
        }

        Frame newFrame = isFinalFrame(frameNo) ? FinalFrame.of(pins) : NormalFrame.of(pins);
        addNext(newFrame);

        return nextPlayFrameNo(frameNo);
    }

    private int nextPlayFrameNo(int frameNo) {
        return frames.getLast().isEnd() ? frameNo + 1 : frameNo;
    }

    private boolean remainDelivery(int frameNo) {
        return frames.size() == frameNo;
    }

    private boolean isFinalFrame(int frameNo) {
        return frameNo == TOTAL_FRAME_COUNT;
    }

    public boolean isEnd() {
        return frames.size() == TOTAL_FRAME_COUNT && frames.getLast().isEnd();
    }

    public Stream<Frame> getFrames() {
        return frames.stream();
    }

    private void addNext(Frame newFrame) {
        if (frames.size() > 0) {
            frames.getLast().next = newFrame;
        }

        frames.add(newFrame);
    }

}
