package bowling.frame.domain;


import bowling.frame.dto.FrameScoreDTO;

public class FrameScoreAssembler {

    private FrameScoreAssembler() {
    }

    public static FrameScoreDTO assemble(FrameScore score) {
        return FrameScoreDTO.of(score.getScore().getScore(), score.isCalculated());
    }

}
