package bowling.domain.frame.dto;

import bowling.domain.frame.EndFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class FrameAssembler {

    private FrameAssembler() {
    }

    public static FrameDTO assemble(Frame frame) {
        if (frame instanceof NormalFrame) {
            return new FrameDTO(((NormalFrame) frame).getState().view());
        }
        EndFrame endFrame = (EndFrame) frame;
        return new FrameDTO(endFrame.getStates().view());
    }

}
