package bowling.domain.dto;

import java.util.List;

public class ScoreSignaturesDto {

    private List<String> scoreSignatures;

    public ScoreSignaturesDto(List<String> scoreSignatures) {
        this.scoreSignatures = scoreSignatures;
    }

    public List<String> getScoreSignatures() {
        return scoreSignatures;
    }
}
