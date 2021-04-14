package bowling.dto;

import bowling.domain.PlayerName;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.stream.Collectors;

public class PlayResult {

    private String playerName;

    private List<FrameResult> frameResults;

    public PlayResult(PlayerName playerName, Frames frames) {
        this.playerName = playerName.name();
        this.frameResults = frames.result();
    }

    public String playerName() {
        return playerName;
    }

    public List<String> allStates() {
        return frameResults.stream()
                .map(FrameResult::state)
                .collect(Collectors.toList());
    }

    public List<ScoreDto> allScores(){
        return frameResults.stream()
                .map(FrameResult::score)
                .collect(Collectors.toList());
    }
}
