package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    private static final int LAST_NORMAL_FRAME_SIZE = 10;

    private final List<Frame> frames;
    private Round round;

    private Frames(List<Frame> frames) {
        this.frames = frames;
        this.round = Round.firstRound();
    }

    public static Frames init() {
        List<Frame> frames = generateNormalFrame();
        frames.add(FinalFrame.from(Round.finalRound()));
        return new Frames(frames);
    }

    private static List<Frame> generateNormalFrame() {
        return IntStream.range(1, LAST_NORMAL_FRAME_SIZE)
                .mapToObj(i -> NormalFrame.from(Round.from(i)))
                .collect(Collectors.toList());
    }

    public int size() {
        return frames.size();
    }

    public boolean isContinue() {
        return !round.isFinalRound();
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

    public void throwBall(int topplePin) {
        Frame nowFrame = frames.get(round.round() - 1);
        nowFrame.throwBall(topplePin);
        if (nowFrame.roundEnded()) {
            round = round.nextRound();
        }
    }

    public int round() {
        return round.round();
    }
}
