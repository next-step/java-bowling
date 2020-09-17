package bowling.domain;

import java.util.LinkedList;
import java.util.List;

import static bowling.domain.LastFrame.LAST_FRAME_NUMBER;

public class Game {
    private String username;
    private LinkedList<Frame> frames;

    private Game(String username) {
        this.username = username;
        this.frames = new LinkedList<Frame>() {{
            add(NormalFrame.from());
        }};
    }

    public static Game start(String username) {
        return new Game(username);
    }

    public String getPlayerName() {
        return username;
    }

    public int getPlayFrameNumber() {
        return getLastFrame().getNumber();
    }

    public Frame hit(int count) {
        Frame frame = getLastFrame().hit(count);

        if (hasNextFrame() && getLastFrame().isFinish()) {
            frames.add(next());
        }

        return frame;
    }

    private Frame next() {
        if (!hasNextFrame()) {
            throw new RuntimeException("다음 프레임이 존재하지 않습니다.");
        }

        int nextFrameNumber = nextFrameNumber();
        return nextFrameNumber == LAST_FRAME_NUMBER ? LastFrame.from() : NormalFrame.of(nextFrameNumber);
    }

    public boolean hasNextFrame() {
        return nextFrameNumber() <= LAST_FRAME_NUMBER;
    }

    private int nextFrameNumber() {
        return getLastFrame().getNumber() + 1;
    }

    private Frame getLastFrame() {
        return frames.getLast();
    }

    public boolean isEnd() {
        return !hasNextFrame() && getLastFrame().isFinish();
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
