package bowling.ui;

import bowling.domain.Frames;
import bowling.domain.Score;
import bowling.domain.ShotHistory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultFrameDto {
    private final String playerName;
    private final List<ShotHistory> shotHistories;
    private final List<Score> scores;

    public ResultFrameDto(Frames frames) {
        this.playerName = frames.getPlayerName();
        this.shotHistories = frames.generateShotHistories();
        this.scores = frames.calculateTotalScore();
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> generateSymbolStrings(String delimiter) {
        return shotHistories
                .stream()
                .map(shotHistory -> shotHistory.generateSymbolString(delimiter))
                .collect(Collectors.toList());
    }

    public List<String> generateScoreStrings() {
        return scores
                .stream()
                .map(Score::toString)
                .collect(Collectors.toList());
    }

    public int getLastFrame() {
        return shotHistories.size();
    }
}
