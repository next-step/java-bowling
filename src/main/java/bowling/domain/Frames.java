package bowling.domain;

import java.util.ArrayList;
import java.util.List;

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

//    public Frames next(int position) {
//        if (position == 10) {
//            frames.add(Frame.ready(position).first(10));
//        }
//        return
//    }

    public void shot(int position, int score) {
        this.frames.set(position, Frame.ready(position).first(score));
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }

    public int size() {
        return frames.size();
    }
}
