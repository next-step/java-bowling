package bowling.frame.domain;


import bowling.frame.dto.FrameDTO;

public class FrameAssembler {

    private FrameAssembler() {
    }

    public static FrameDTO assemble(Frame frame) {

        if (frame instanceof NormalFrame) {
            return new FrameDTO(((NormalFrame) frame).getState().view(), ScoreAssembler.assemble(frame.getScore()));
        }
        EndFrame endFrame = (EndFrame) frame;
        return new FrameDTO(endFrame.getStates().view(), ScoreAssembler.assemble(frame.getScore()));
    }

}
