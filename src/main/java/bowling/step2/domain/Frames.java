package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Frames {
    private static final int LAST_FRAME = 10;

    private final List<Frame> frames;
    private final Player player;

    private Frames (List<Frame> frames, Player player) {
        this.frames = frames;
        this.player = player;
    }

    public static Frames of (List<Frame> frames, Player player) {
        return new Frames(new ArrayList<>(), player);
    }

    public void next (Frame frame) {
        frames.add(frame.next());
    }
}