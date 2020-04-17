package bowling.domain.frame;

import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int TOTAL_NORMAL_FRAME = 9;

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
                .orElseGet(() -> null);
    }

    public String getPlayerName() {
        return player.getName();
    }

    private List<Frame> initFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 1; i <= TOTAL_NORMAL_FRAME; i++) {
            frames.add(new NormalFrame(i));
        }
        frames.add(new FinalFrame(TOTAL_NORMAL_FRAME + 1));

        return frames;
    }
}
