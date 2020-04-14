package bowling.dto;

import bowling.BowlingFrames;
import bowling.BowlingGame;
import bowling.Player;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameResult {

    private final String name;
    private final List<FrameScoreConsoleResult> frameScoreConsoleResults;

    private BowlingGameResult(final String name, final List<FrameScoreConsoleResult> frameScoreConsoleResults) {
        this.name = name;
        this.frameScoreConsoleResults = frameScoreConsoleResults;
    }

    public static BowlingGameResult newInstance(final BowlingGame bowlingGame) {
        Player player = bowlingGame.getPlayer();

        BowlingFrames bowlingFrames = bowlingGame.getBowlingFrames();

        List<FrameScoreConsoleResult> frameScoreConsoleResults = bowlingFrames.getFrames()
                .stream()
                .map(bowlingFrame -> FrameScoreConsoleResult.newInstance(bowlingFrame.getFrameScore()))
                .collect(Collectors.toList());

        return new BowlingGameResult(player.getName(), frameScoreConsoleResults);
    }

    public String getName() {
        return name;
    }

    public List<FrameScoreConsoleResult> getFrameScores() {
        return frameScoreConsoleResults;
    }
}
