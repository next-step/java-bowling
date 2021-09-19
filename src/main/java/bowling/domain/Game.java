package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int BOWLING_FRAME_COUNT = 10;
    private Player player;
    private List<Frame> frames = new ArrayList<>();

    public Game(Player player) {
        this.player = player;
        for (int i = 0; i < BOWLING_FRAME_COUNT; i++) {
            frames.add(new Frame(i + 1));
        }
    }

    public List<Frame> frames() {
        return frames;
    }

    public Frame nextFrame() {
        return frames.stream()
                .filter(frame -> frame.remain())
                .findFirst()
                .orElse(frames.get(BOWLING_FRAME_COUNT - 1));
    }

    public boolean isOver() {
        return nextFrame().order() == BOWLING_FRAME_COUNT &&
                !nextFrame().remain();
    }

    public void pitch(int score) {
        nextFrame().pitch(score);
    }
}
