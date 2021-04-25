package bowling.domain.frame;

import bowling.dto.FrameDTO;
import bowling.dto.FramesDTO;

import java.util.ArrayList;
import java.util.List;

import static bowling.domain.frame.Frame.FIRST_FRAME_NO;
import static bowling.domain.frame.Frame.LAST_FRAME_NO;

public class Frames {
    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        initFrames(frames);
        return new Frames(frames);
    }

    private static Frame initFrames(List<Frame> frames) {
        Frame frame = initFirstFrame(frames);
        for (int i = FIRST_FRAME_NO; i < LAST_FRAME_NO; i++) {
            frame = frame.getNext();
            frames.add(frame);
        }
        return frame;
    }

    private static Frame initFirstFrame(List<Frame> frames) {
        Frame frame = Frame.init();
        frames.add(frame);
        return frame;
    }

    public Frame nthFrame(int index) {
        return frames.get(index);
    }

    public void bowl(int pitch, int index) {
        nthFrame(index).bowl(pitch);
    }

    public FramesDTO exportFramesDTO() {
        List<FrameDTO> frames = new ArrayList<>();
        for (Frame frame : this.frames) {
            frames.add(frame.exportFrameDTO());
        }
        return new FramesDTO(frames);
    }
}
