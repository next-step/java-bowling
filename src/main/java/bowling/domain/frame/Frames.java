package bowling.domain.frame;

import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int LAST_FRAME_INDEX = 9;
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
                .filter(frame -> frame.isThrowable())
                .findFirst()
                .orElseGet(() -> frames.get(LAST_FRAME_INDEX));
    }

    public String getPlayerName() {
        return player.getName();
    }

    private List<Frame> initFrames() {
        List<Frame> tmpframes = new ArrayList<>();
        for (int i = 1; i <= TOTAL_NORMAL_FRAME; i++) {
            tmpframes.add(new NormalFrame(i));
        }
        tmpframes.add(new FinalFrame(TOTAL_NORMAL_FRAME + 1));
        return tmpframes;
    }
}
