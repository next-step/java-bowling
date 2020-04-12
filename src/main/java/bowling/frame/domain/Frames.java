package bowling.frame.domain;

import bowling.player.domain.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int ONE = 1;
    private static final int NORMAL_FRAME = 9;
    private static final int LAST_FRAME = 10;

    private final Player player;
    private final List<Frame> frames;

    private Frames(Player player, List<Frame> frames) {
        this.player = player;
        this.frames = frames;
    }

    public static Frames init(Player player) {
        return new Frames(player, new ArrayList<>(Collections.singletonList(Frame.normal())));
    }

    public void bowl(int felledPins) {
        Frame current = getCurrent();
        current.bowl(felledPins);
        if (current.isFinished() && !isDone()) {
            generate();
        }
    }

    private void generate() {
        if (frames.size() < NORMAL_FRAME) {
            frames.add(Frame.normal());
            return;
        }
        frames.add(Frame.last());
    }

    private Frame getCurrent() {
        int lastIndex = frames.size() - ONE;
        return frames.get(lastIndex);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getSize() {
        return frames.size();
    }

    public boolean isDone() {
        return frames.size() == LAST_FRAME && getCurrent().isFinished();
    }

}
