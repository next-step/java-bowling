package bowling.step3.domain;

import java.util.HashMap;
import java.util.Map;

public class Frames {
    public static final int START_FRAME_INDEX = 1;
    public static final int LAST_FRAME_INDEX = 10;

    private final Map<Integer, Frame> frameMap;
    public Frames() {
        Map<Integer, Frame> frameMap = new HashMap<>();
        for (int i = START_FRAME_INDEX; i <= LAST_FRAME_INDEX; i++) {
            frameMap.put(i, getFrame(i));
        }
        this.frameMap = frameMap;
    }

    private Frame getFrame(int i) {
        if (i == LAST_FRAME_INDEX) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    public boolean isEndedFrame(int frameNum) {
        return this.frameMap.get(frameNum).isEndedFrame();
    }

    public void add(int frameNum, int count){
        this.frameMap.get(frameNum).add(count);
    }

    public Map<Integer, Frame> frameMap() {
        return new HashMap<>(this.frameMap);
    }

}
