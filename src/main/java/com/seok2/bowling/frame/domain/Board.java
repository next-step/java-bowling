package com.seok2.bowling.frame.domain;

import com.seok2.bowling.pin.domain.Pin;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Board {

    public static final int END_FRAME_COUNT = 10;

    private static final int NORMAL_FRAME_COUNT = 9;
    private final Deque<Frame> frames;

    private Board(Deque<Frame> frames) {
        this.frames = frames;
    }

    public static Board init() {
        return new Board(new LinkedList<>(Arrays.asList(Frame.normal())));
    }

    public void roll(Pin felled) {
        Frame current = frames.getLast();
        current.roll(felled);
        if (current.isEnd() && !isGameOver()) {
            generate();
        }
    }

    private void generate() {
        if (frames.size() < NORMAL_FRAME_COUNT) {
            frames.add(Frame.normal());
            return;
        }
        frames.add(Frame.end());
    }

    public int size() {
        return frames.size();
    }

    public boolean isGameOver() {
        return frames.size() == END_FRAME_COUNT && frames.getLast().isEnd();
    }

    protected Deque<Frame> getFrames() {
        return frames;
    }
}
