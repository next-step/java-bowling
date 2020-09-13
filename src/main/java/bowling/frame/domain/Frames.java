package bowling.frame.domain;

import bowling.global.exception.OutOfFrameRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.pin.domain.Pins;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

    public static int TOTAL_FRAME_NUMBER = 1;
    public static int PITCH_COUNT = 2;

    private List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames nextFrame(List<Frame> frames, Pins pins) {
        Frame frame = createFrame(TOTAL_FRAME_NUMBER, pins);
        increaseTotalFrameNumber(frame, pins);
        frames.add(frame);
        return new Frames(frames);
    }

    private static Frame createFrame(int frameNumber, Pins pins) {
        if (frameNumber == 10) {
            return FinalFrame.finalFrame(pins);
        }
        if (frameNumber > 0 && frameNumber < 10) {
            return NormalFrame.newFrame(frameNumber, pins);
        }
        throw new OutOfFrameRangeException(ExceptionMessage.INVALID_NOMAL_FRAME_NUMBER);
    }

    private static int increaseTotalFrameNumber(Frame frame, Pins pins) {
        if (frame.isFinal() && TOTAL_FRAME_NUMBER == 10) {
//            TOTAL_FRAME_NUMBER += 1;
            System.out.println("finalFrame");
        }
        if (!frame.isFinal() && pins.size() == 2) {
            TOTAL_FRAME_NUMBER += 1;
        }
        return TOTAL_FRAME_NUMBER;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
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
