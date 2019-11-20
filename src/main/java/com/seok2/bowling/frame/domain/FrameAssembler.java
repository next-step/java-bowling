package com.seok2.bowling.frame.domain;


import com.seok2.bowling.frame.dto.FrameDTO;

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
        EndFrame endFrame = (EndFrame) frame;
        return new FrameDTO(endFrame.getStates().view(), FrameScoreAssembler.assemble(frame.getScore()));
    }

    private static FrameDTO assembleNormalFrame(Frame frame) {
        NormalFrame normalFrame = (NormalFrame) frame;
        return new FrameDTO(normalFrame.getState().view(), FrameScoreAssembler.assemble(frame.getScore()));
    }

}
