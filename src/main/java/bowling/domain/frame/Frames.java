package bowling.domain.frame;

import bowling.Pin;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    private static final int ROUND_START_INDEX = 0;
    private static final int GENERAL_ROUND_END_INDEX = 8;

    private static final int MAX_ROUND_INDEX = 10;

    private final List<Frame> frames;

    public Frames(List<Frame> frames) {

        if (frames.size() > MAX_ROUND_INDEX) {
            throw new IllegalArgumentException("프레임은 최대 10라운드 까지 가능해요.");
        }

        this.frames = frames;
    }

    public static Frames create() {
        List<Frame> initFrames = IntStream.range(ROUND_START_INDEX, GENERAL_ROUND_END_INDEX)
            .boxed()
            .map(index -> new GeneralFrame())
            .collect(Collectors.toList());

        initFrames.add(new FinalFrame());

        return new Frames(initFrames);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(this.frames);
    }

    public Frame bowl(int index, Pin pin) {
        Frame bowlAfterFrame = get(index).bowl(pin);
        frames.set(index, bowlAfterFrame);

        return bowlAfterFrame;
    }

    public Frame get(int index) {
        return this.frames.get(index);
    }
}
