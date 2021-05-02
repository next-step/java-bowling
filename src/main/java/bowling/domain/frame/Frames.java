package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.dto.FrameDTO;
import bowling.dto.FramesDTO;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.frame.Frame.FIRST_FRAME_NO;
import static bowling.domain.frame.Frame.LAST_FRAME_NO;

public class Frames {
    private final List<Frame> frameList;

    private Frames(List<Frame> frames) {
        this.frameList = frames;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        initFrames(frames);
        return new Frames(frames);
    }

    private static Frame initFrames(List<Frame> frames) {
        Frame frame = initFirstFrame(frames);
        for (int i = FIRST_FRAME_NO; i < LAST_FRAME_NO; i++) {
            frame = frame.next();
            frames.add(frame);
        }
        return frame;
    }

    private static Frame initFirstFrame(List<Frame> frames) {
        Frame frame = NormalFrame.init();
        frames.add(frame);
        return frame;
    }

    public Frame nthFrame(int frameNo) {
        return frameList.get(frameNo);
    }

    public void bowl(Pins pitch, int index) {
        nthFrame(index).bowl(pitch);
    }

    public FramesDTO exportFramesDTO() {
        List<FrameDTO> frames = new ArrayList<>();
        for (Frame frame : this.frameList) {
            frames.add(frame.exportFrameDTO());
        }
        return new FramesDTO(frames);
    }
}
