package bowling.frame.domain;

import bowling.global.exception.OutOfFrameRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.pin.domain.Pins;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    public static int TOTAL_FRAME_NUMBER = 1;
    public static int PITCH_COUNT = 2;

    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    private static Frame createFrame(int frameNumber, Pins pins) {
        if (frameNumber == 10) {
            return FinalFrame.finalFrame();
        }
        if (frameNumber >= 1 || frameNumber >= 9) {
            return NormalFrame.newFrame(frameNumber, pins);
        }
        throw new OutOfFrameRangeException(ExceptionMessage.INVALID_NOMAL_FRAME_NUMBER);
    }

    public static List<Frame> addFrame(List<Frame> frames, Pins pins) {
        Frame frame = createFrame(TOTAL_FRAME_NUMBER, pins);
        if (!frame.isFinal() && pins.size() == 2) {
            TOTAL_FRAME_NUMBER += 1;
        }
        frames.add(frame);
        return frames;
    }

    @Override
    public String toString() {
        return String.valueOf(frames);
    }
}
