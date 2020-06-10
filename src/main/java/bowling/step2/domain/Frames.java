package bowling.step2.domain;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Frames {
    private static final int LAST_FRAME = 10;

    private final List<Frame> frames;

    private Frames (List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init (Players players) {
        return IntStream.rangeClosed(1, LAST_FRAME)
                        .mapToObj(frame -> Frame.init(frame, players))
                        .collect(collectingAndThen(toList(), Frames::new));
    }

    public Stream<Frame> stream () {
        return frames.stream();
    }

    public Stream<FrameScore> scoresOfPlayerStream (PlayerName playerName) {
        return stream()
                .map(frame -> frame.scoreOfPlayer(playerName));
    }
}