package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Frames {

    private static final int FIRST_FRAME_INDEX = 0;
    private static final int LAST_FRAME_INDEX = 10;

    private List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        for (int i = FIRST_FRAME_INDEX; i < LAST_FRAME_INDEX; i++) {
            frames.add(Frame.ready(i));
        }
        return Frames.of(frames);
    }

    public static Frames initiate() {
        List<Frame> frames = new ArrayList<>();
        frames.add(Frame.first(0, new Random().nextInt(5)));
        return Frames.of(frames);
    }

    public Frames next() {
        Frame recentlyFrame = frames.get(frames.size() - 1);
        if (recentlyFrame.isRemain()) {
            Frame frame = recentlyFrame.second(new Random().nextInt(5));
            frames.set(frames.size() - 1, frame);
            return Frames.of(frames);
        }
        Frame frameWithScore = Frame.first(frames.size(), new Random().nextInt(5));
        frames.add(frameWithScore);
        return Frames.of(frames);
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }

    public int size() {
        return frames.size();
    }
}
