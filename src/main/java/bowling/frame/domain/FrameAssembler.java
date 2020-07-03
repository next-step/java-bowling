package bowling.frame.domain;

import bowling.frame.dto.FrameDTO;

public class FrameAssembler {
    private FrameAssembler() {
    }

    public static FrameDTO assemble(Frame frame) {
        if (frame instanceof NormalFrame) {
            return assembleNormalFrame(frame);
        }
        return assembleEndFrame(frame);
    }

    private static FrameDTO assembleEndFrame(Frame frame) {
        FinalFrame finalFrame = (FinalFrame) frame;
        return new FrameDTO(finalFrame.getStates().view(), FrameScoreAssembler.assemble(frame.getScore()));
    }

    private static FrameDTO assembleNormalFrame(Frame frame) {
        NormalFrame normalFrame = (NormalFrame) frame;
        return new FrameDTO(normalFrame.getState().view(), FrameScoreAssembler.assemble(frame.getScore()));
    }

}
