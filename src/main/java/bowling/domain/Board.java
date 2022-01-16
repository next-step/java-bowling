package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.LastFrame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    public static int BOARD_MAX_SIZE = 10;

    private final List<Frame> frames = new ArrayList<>();

    public void init() {
        for (int index = 0; index < 9; index++) {
            frames.add(new NormalFrame());
        }
        frames.add(new LastFrame());
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public Frame getRecentFrame(int index) {
        return frames.get(index);
    }
}

