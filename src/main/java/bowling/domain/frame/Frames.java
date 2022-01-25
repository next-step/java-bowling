package bowling.domain.frame;

import bowling.domain.frame.DefaultFrame;
import bowling.domain.frame.LastDefaultFrame;
import bowling.domain.frame.NormalDefaultFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    public static int BOARD_MAX_SIZE = 10;

    private final List<Frame> frames = new ArrayList<>();

    public void init() {
        for (int index = 0; index < 9; index++) {
            frames.add(new NormalDefaultFrame());
        }
        frames.add(new LastDefaultFrame());
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public Frame getRecentFrame(int index) {
        return frames.get(index);
    }
}

