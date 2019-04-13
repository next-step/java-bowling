package bowling.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    static final String FRAME_DELIMETER = "|";
    static final String FRAME_FORMAT = "  %-4s";
    private static final String EMPTY_PITCH = "      ";
    private static final int TOTAL_FRAMES = 10;
    private static final int FIRST_FRAME_ORDER = 1;

    private final List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>(TOTAL_FRAMES);
    }

    @Override
    public String toString() {
        return IntStream.range(0, TOTAL_FRAMES)
                .mapToObj(this::frameToString)
                .collect(Collectors.joining(FRAME_DELIMETER, FRAME_DELIMETER, FRAME_DELIMETER));
    }

    void pitch(PinScore pinScore) {
        validateState();

        int sizeOfFrames = this.frames.size();

        if (this.frames.isEmpty()) {
            addNewNormalFrame(pinScore);
            return;
        }

        if (sizeOfFrames == (TOTAL_FRAMES - 1)) {
            addNewFinalFrame(pinScore);
            return;
        }

        Frame lastFrame = getLastFrameOfFrames();

        if (lastFrame.isEnd()) {
            addNewNormalFrame(pinScore);
            return;
        }

        lastFrame.pitch(pinScore);
    }

    boolean isEnd() {
        if (this.frames.size() < TOTAL_FRAMES) {
            return false;
        }

        int indexOfFinalFrame = TOTAL_FRAMES - 1;
        Frame finalFrame = frames.get(indexOfFinalFrame);

        return finalFrame.isEnd();
    }

    int getCurrentFrameOrder() {
        if (this.frames.isEmpty()) {
            return FIRST_FRAME_ORDER;
        }

        Frame lastFrame = getLastFrameOfFrames();
        return (lastFrame.isEnd() ? this.frames.size() : (this.frames.size() + 1));
    }

    private void validateState() {
        if (isEnd()) {
            throw new IllegalStateException("There's no available frame");
        }
    }

    private boolean addNewNormalFrame(PinScore pinScore) {
        return this.frames.add(new NormalFrame(pinScore));
    }

    private boolean addNewFinalFrame(PinScore pinScore) {
        return this.frames.add(new FinalFrame(pinScore));
    }

    private Frame getLastFrameOfFrames() {
        return this.frames.get(this.frames.size() - 1);
    }

    private String frameToString(int index) {
        int maxIndex = this.frames.size() - 1;

        if (index <= maxIndex) {
            Frame frame = this.frames.get(index);
            return String.format(FRAME_FORMAT, frame.toString());
        }

        return EMPTY_PITCH;
    }
}
