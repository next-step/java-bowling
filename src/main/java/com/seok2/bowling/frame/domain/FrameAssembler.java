package com.seok2.bowling.frame.domain;


import com.seok2.bowling.frame.dto.FrameDTO;

public class FrameAssembler {

    private FrameAssembler() {
    }

    public static FrameDTO assemble(Frame frame) {
        if (frame instanceof NormalFrame) {
            return new FrameDTO(((NormalFrame) frame).getState().view());
        }
        EndFrame f = (EndFrame) frame;
        return new FrameDTO(f.getStates().view());
    }

}
