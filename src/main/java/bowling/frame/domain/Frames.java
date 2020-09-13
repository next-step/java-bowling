package bowling.frame.domain;

import bowling.global.exception.OutOfFrameRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.pin.domain.Pins;

import java.util.List;
import java.util.Objects;

import static bowling.global.utils.CommonConstant.NUMBER_TWO;
import static bowling.global.utils.CommonConstant.NUMBER_ZERO;

public class Frames {

    public static int CURRENT_FRAME_NUMBER = 1;
    public static int PITCH_COUNT = 2;
    public static final int FINAL_FRAME_COUNT = 10;
    private static final int INCREASE_FRAME_NUMBER = 1;

    private List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames nextFrame(List<Frame> frames, Pins pins) {
        Frame frame = createFrame(CURRENT_FRAME_NUMBER, pins);
        increaseTotalFrameNumber(frame, pins);
        frames.add(frame);
        return new Frames(frames);
    }

    private static Frame createFrame(int frameNumber, Pins pins) {
        if (frameNumber == FINAL_FRAME_COUNT) {
            return FinalFrame.finalFrame(pins);
        }
        if (frameNumber > NUMBER_ZERO && frameNumber < FINAL_FRAME_COUNT) {
            return NormalFrame.newFrame(frameNumber, pins);
        }
        throw new OutOfFrameRangeException(ExceptionMessage.INVALID_NOMAL_FRAME_NUMBER);
    }

    private static int increaseTotalFrameNumber(Frame frame, Pins pins) {
        if (CURRENT_FRAME_NUMBER == FINAL_FRAME_COUNT && (frame.isFinal() && pins.size() >= NUMBER_TWO)) {
            CURRENT_FRAME_NUMBER += INCREASE_FRAME_NUMBER;
        }
        if (!frame.isFinal() && pins.size() == NUMBER_TWO) {
            CURRENT_FRAME_NUMBER += INCREASE_FRAME_NUMBER;
        }
        return CURRENT_FRAME_NUMBER;
    }

    public int size() {
        return frames.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                '}';
    }
}
