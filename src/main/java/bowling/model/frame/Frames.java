package bowling.model.frame;

import bowling.model.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(NormalFrame.first());
    }

    public void bowl(Pin pin) {
       Frame frame = getCurrentFrame();
        frame.bowl(pin);
        if(frame.isFinished() && !frame.isFinalFrame()){
            frames.add(frame.nextFrame());
        }
    }

    public Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
