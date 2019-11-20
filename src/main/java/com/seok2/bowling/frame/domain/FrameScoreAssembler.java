package com.seok2.bowling.frame.domain;

import com.seok2.bowling.frame.dto.FrameScoreDTO;

public class FrameScoreAssembler {

    private FrameScoreAssembler() {
    }

    public static FrameScoreDTO assemble(FrameScore score) {
        return FrameScoreDTO.of(score.getScore().getScore(), score.isCalculated());
    }

}
