package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

import static bowling.Constants.FIRST_FRAME_NUMBER;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(NormalFrame.create(FIRST_FRAME_NUMBER));
    }

    public void play(int felledPin) {
        Frame frame = getCurrentFrame();
        frame.play(felledPin);

        if(!frame.isLastFrame() && frame.isEndedFrame()) {
            Frame nextFrame = frame.getNext();
            addFrame(nextFrame);
        }
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    private void addFrame(Frame frame) {
        if(frame != null) {
            frames.add(frame);
        }
    }

    public boolean isEnd() {
        return getCurrentFrame().isLastFrame();
    }

    public int getCurrentFrameNumber() {
        return frames.size();
    }

    public List<Frame> getValue() {
        return frames;
    }
}
