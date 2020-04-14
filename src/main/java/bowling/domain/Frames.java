package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int TOTAL_FRAME = 10;

    private final Player player;
    private final List<Frame> frames;

    public Frames(Player player) {
        this.player = player;
        this.frames = initFrames();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Frame getNextFrame() {
        return this.frames.stream()
                .filter(frame -> frame.isThrowable() == true)
                .findFirst()
                .orElseGet(null);
    }

    private List<Frame> initFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i <= TOTAL_FRAME; i++) {
            frames.add(new Frame());
        }
        return frames;
    }
}
