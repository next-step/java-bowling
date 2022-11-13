package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
        frames.add(new NormalFrame());
    }

    public Frames(Frame... frames) {
        this.frames = new ArrayList<>();
        this.frames.addAll(Arrays.asList(frames));
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void addScore(int index, Score score) {
        frames.get(index).addScore(score);
    }

    public boolean end(int index) {
        boolean isEnd = this.frames.get(index).end();
        if (isEnd) {
            frames.add(FrameFactory.create(index + 1));
        }
        return isEnd;
    }
}
