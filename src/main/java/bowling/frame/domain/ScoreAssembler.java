package bowling.frame.domain;

import bowling.frame.dto.ScoreDTO;

public class ScoreAssembler {

    private ScoreAssembler() {
    }

    public static ScoreDTO assemble(Score score) {
        return new ScoreDTO(score.getScore(), score.isPending());
    }

}
