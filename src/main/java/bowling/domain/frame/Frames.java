package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Round;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {

    private static final int ROUND_START_INDEX = 0;
    private static final int ONE = 1;
    private static final int MAX_OF_GENERAL_ROUND = 9;
    private static final int MAX_OF_ROUND = 10;

    private Frame frame;

    public Frames(List<Frame> frames) {

        if (getLastFrame(frames) instanceof GeneralFrame) {
            throw new IllegalArgumentException("마지막 프레임은 GeneralFrame이 올 수 없어요.");
        }
        if (frames.size() != MAX_OF_ROUND) {
            throw new IllegalArgumentException("프레임은 10라운드 이어야 해요.");
        }

        Frame firstFrame = frames.get(ROUND_START_INDEX);
        setNextFrameChaining(frames, firstFrame);

        this.frame = firstFrame;
    }

    private void setNextFrameChaining(List<Frame> frames, Frame firstFrame) {
        Frame frame = firstFrame;

        for (int round = ONE; round < MAX_OF_ROUND; round++) {
            Frame nextFrame = frames.get(round);
            frame.setNext(nextFrame);
            frame = nextFrame;
        }
    }

    public static Frames create() {
        List<Frame> initFrames = IntStream.range(ROUND_START_INDEX, MAX_OF_GENERAL_ROUND)
            .boxed()
            .map(index -> new GeneralFrame())
            .collect(Collectors.toList());

        initFrames.add(new FinalFrame());

        return new Frames(initFrames);
    }

    public List<Frame> getFrames() {
        List<Frame> frames = new ArrayList<>();

        Frame findFrame = this.frame;
        frames.add(findFrame);

        for (int index = ONE; index < MAX_OF_ROUND; index++) {
            findFrame = getNextFrame(findFrame);
            frames.add(findFrame);
        }

        return frames;
    }

    public Frame bowl(int index, Pin pin) {
        return bowl(Round.of(index), pin);
    }

    public Frame bowl(Round round, Pin pin) {
        Frame originalFrame = getFrame(round);
        if (originalFrame.isClosed()) {
            throw new BowlingProgressException();
        }

        Frame updateFrame = originalFrame.bowl(pin);

        if (updateFrame instanceof GeneralFrame) {
            updateFrame.setNext(getNextFrame(originalFrame));
        }

        if (round.isStartRound()) {
            this.frame = updateFrame;
            return updateFrame;
        }

        getBeforeFrame(round).setNext(updateFrame);
        return updateFrame;
    }

    public Frame getFrame(Round round) {
        Frame findFrame = this.frame;
        for (int index = ONE; index <= round.getIndex(); index++) {
            findFrame = getNextFrame(findFrame);
        }

        return findFrame;
    }


    private Frame getNextFrame(Frame targetFrame) {
        return targetFrame.getNext().orElseThrow(FrameNotFoundException::new);
    }

    private Frame getBeforeFrame(Round round) {
        return getFrame(round.before());
    }

    private Frame getLastFrame(List<Frame> frames) {
        return frames.get(frames.size() - ONE);
    }
}
