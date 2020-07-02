package bowling.domain;

import java.util.LinkedList;
import java.util.function.Consumer;
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

    public void forEachFrame(Consumer<? super Frame> consumer) {
        frames.forEach(consumer);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getCurrentFrameNo() {
        return frames.size();
    }
}
