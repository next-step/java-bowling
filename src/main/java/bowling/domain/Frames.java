package bowling.domain;

import java.util.LinkedList;
import java.util.stream.Stream;

public class Frames {
    public static final int FIRST_FRAME = 1;
    public static final int FINAL_FRAME = 10;
    private Player player;
    private LinkedList<Frame> frames;

    public Frames(Player player) {
        this.player = player;
        this.frames = new LinkedList<>();
        frames.add(Frame.first());
    }

    public boolean bowling(Pin pin) {
        State state = frames.getLast().bowling(pin);
        if (state.isFinish() && !frames.getLast().isGameEnd()) {
            frames.add(frames.getLast().next());
        }
        return frames.getLast().isGameEnd();
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
