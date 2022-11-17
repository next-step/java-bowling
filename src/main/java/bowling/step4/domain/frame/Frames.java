package bowling.step4.domain.frame;

import java.util.HashMap;
import java.util.Map;

public class Frames {
    private static final int START_FRAME_INDEX = 1;
    private static final int LAST_FRAME_INDEX = 10;

    private final Map<Integer, Frame> frameMap;

    public Frames() {
        Map<Integer, Frame> frameMap = new HashMap<>();
        for (int i = START_FRAME_INDEX; i <= LAST_FRAME_INDEX; i++) {
            frameMap.put(i, getNewFrame(i));
        }
        this.frameMap = frameMap;
    }

    private Frame getNewFrame(int i) {
        if (i == LAST_FRAME_INDEX) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    public boolean isEndedFrame(int frameNum) {
        return getFrame(frameNum).isEndedFrame();
    }

    public void bowl(int frameNum, int count) {
        getFrame(frameNum).add(count);
    }

    private Frame getFrame(int index) {
        return this.frameMap.get(index);
    }

    public Map<Integer, Frame> frameMap() {
        return new HashMap<>(this.frameMap);
    }
}
