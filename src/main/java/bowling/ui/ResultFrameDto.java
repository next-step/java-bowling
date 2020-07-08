package bowling.ui;

import bowling.domain.Frames;
import bowling.domain.Score;

import java.util.List;
import java.util.stream.Collectors;

public class ResultFrameDto {
    private static final String delimiter = "|";
    private final String playerName;
    private final List<String> symbolStrings;
    private final List<String> scoreStrings;

    public ResultFrameDto(Frames frames) {
        this.playerName = frames.getPlayerName();
        this.symbolStrings = generateSymbolStrings(frames);
        this.scoreStrings = generateScoreStrings(frames);
    }

    private List<String> generateScoreStrings(Frames frames) {
        return frames
                .calculateTotalScore()
                .stream()
                .map(Score::toString)
                .collect(Collectors.toList());
    }

    private List<String> generateSymbolStrings(Frames frames) {
        return frames
                .generateShotHistories()
                .stream()
                .map(shotHistory -> shotHistory.generateSymbolString(delimiter))
                .collect(Collectors.toList());
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getSymbolStrings() {
        return symbolStrings;

    }

    public List<String> getScoreStrings() {
        return scoreStrings;

    }

    public int getLastFrame() {
        return symbolStrings.size();
    }
}
