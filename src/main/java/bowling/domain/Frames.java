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

    public static Frames initiate(int score) {
        List<Frame> frames = new ArrayList<>();
        frames.add(Frame.first(0, score));
        return Frames.of(frames);
    }

    public Frames next(int score) {
        Frame frameWithScore = Frame.first(frames.size(), score);
        frames.add(frameWithScore);
        return Frames.of(frames);
    }

    public Frames nextSecond(int score) {
        Frame recentlyFrame = frames.get(frames.size() - 1);
        if (recentlyFrame.isRemain()) {
            Frame frame = recentlyFrame.second(score);
            frames.set(frames.size() - 1, frame);
        }
        return Frames.of(frames);
    }

    public boolean isRemain() {
        Frame recentlyFrame = frames.get(frames.size() - 1);
        return recentlyFrame.isRemain();
    }

    public int getRemainScore() {
        Frame recentlyFrame = frames.get(frames.size() - 1);
        return recentlyFrame.getPoint();
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }

    public int size() {
        return frames.size();
    }
}
