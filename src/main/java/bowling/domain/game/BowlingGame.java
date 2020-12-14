package bowling.domain.game;

import bowling.domain.FallingPinCount;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.state.State;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGame {

    private static final int START_INDEX = 1;

    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player) {
        this.player = player;
        this.frames = Frames.of(getFrames());
    }

    private LinkedList<Frame> getFrames() {
        return IntStream.of(START_INDEX)
            .mapToObj(Frame::new)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public String playersName() {
        return player.name();
    }

    public int getCurrentFrameSequence() {
        return frames.getCurrentFrameSequence();
    }

    public Map<Integer, State> stateGroupedBy() {
        Map<Integer, State> grouped = frames.stateGroupBy();
        return Collections.unmodifiableMap(grouped);
    }

    public boolean isGameFinished() {
        return frames.isGameFinished();
    }

    public void play(FallingPinCount currentFramePitch) {
        frames.saveSate(currentFramePitch);
    }
}
