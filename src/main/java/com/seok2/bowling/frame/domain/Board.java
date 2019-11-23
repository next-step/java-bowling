package com.seok2.bowling.frame.domain;

import com.seok2.bowling.pin.domain.Pin;
import com.seok2.bowling.user.domain.User;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Board {

    public static final int END_FRAME_COUNT = 10;
    private static final int NORMAL_FRAME_COUNT = 9;

    private final ScorePublisher publisher = new ScorePublisher();
    private final User user;
    private final Deque<Frame> frames;

    private Board(User user, Deque<Frame> frames) {
        this.frames = frames;
        this.user = user;
    }

    public static Board init(String user) {
        return init(User.of(user));
    }

    public static Board init(User user) {
        return new Board(user, new LinkedList<>(Arrays.asList(Frame.normal())));
    }

    public void roll(Pin felled) {
        Frame current = frames.getLast();
        current.roll(felled);
        publisher.update(felled);
        if (current.isEnd() && !isGameOver()) {
            generate();
        }
    }

    private void generate() {
        ((NormalFrame) frames.getLast()).createScore(publisher);
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
        return this.frames;
    }

    protected User getUser() {
        return this.user;
    }
}
