package bowling.domain;

import java.util.LinkedList;
import java.util.stream.Stream;

public class Frames {
    private Player player;
    private LinkedList<Frame> frames;

    public Frames(Player player) {
        this.player = player;
        this.frames = new LinkedList<>();
        frames.add(Frame.first());
    }

    public boolean bowling(Pin pin) {
        State state = getLastFrame().bowling(pin);
        if (state.isFinish() && !getLastFrame().isGameEnd()) {
            frames.add(getLastFrame().next());
        }
        return getLastFrame().isGameEnd();
    }

    private Frame getLastFrame() {
        return frames.getLast();
    }

    public Stream<Frame> stream() {
        return frames.stream();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getCurrentFrameNo() {
        return frames.size();
    }
}
