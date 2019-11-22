package com.seok2.bowling.frame.domain;

import com.seok2.bowling.frame.dto.ScoreDTO;

public class ScoreAssembler {

    private ScoreAssembler() {
    }

    public static ScoreDTO assemble(Score score) {
        return new ScoreDTO(score.getScore(), score.isPending());
    }

}
