package bowling.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameFrames {

    private static final int COUNT_OF_FRAME = 10;

    private Map<Integer, Frame> frames;

    private GameFrames(Map<Integer, Frame> frames) {
        this.frames = frames;
    }

    public static GameFrames init() {
        Map<Integer, Frame> frames = new LinkedHashMap<>(COUNT_OF_FRAME);
        return new GameFrames(frames);
    }

    public GameFrames put(int count, Frame frame) {
        frames.put(count, frame);
        return new GameFrames(frames);
    }

    public Map<Integer, Frame> getFrames() {
        return frames;
    }

    public int size() {
        return frames.size();
    }
}
