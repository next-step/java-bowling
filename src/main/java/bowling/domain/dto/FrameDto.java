package bowling.domain.dto;

import java.util.List;

public class FrameDto {

    private final String playerName;
    private final List<String> scoreSignatures;
    private final List<Integer> scores;

    public FrameDto(String playerName, List<String> scoreSignatures, List<Integer> scores) {
        this.playerName = playerName;
        this.scoreSignatures = scoreSignatures;
        this.scores = scores;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getScoreSignatures() {
        return scoreSignatures;
    }

    public List<Integer> getScores() {
        return scores;
    }
}
