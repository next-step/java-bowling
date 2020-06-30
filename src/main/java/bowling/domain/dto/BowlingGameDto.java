package bowling.domain.dto;

import java.util.List;

public class BowlingGameDto {

    private String playerName;
    private List<ScoreSignaturesDto> scoreSignatures;
    private List<Integer> scores;

    public BowlingGameDto(String playerName, List<ScoreSignaturesDto> scoreSignatures, List<Integer> scores) {
        this.playerName = playerName;
        this.scoreSignatures = scoreSignatures;
        this.scores = scores;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<ScoreSignaturesDto> getScoreSignatures() {
        return scoreSignatures;
    }

    public List<Integer> getScores() {
        return scores;
    }
}
