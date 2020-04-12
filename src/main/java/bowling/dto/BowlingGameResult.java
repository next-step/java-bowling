package bowling.dto;

import bowling.BowlingFrames;
import bowling.BowlingGame;
import bowling.Player;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameResult {

    private final String name;
    private final List<FrameScoreResult> frameScoreResults;

    private BowlingGameResult(final String name, final List<FrameScoreResult> frameScoreResults) {
        this.name = name;
        this.frameScoreResults = frameScoreResults;
    }

    public static BowlingGameResult newInstance(final BowlingGame bowlingGame) {
        Player player = bowlingGame.getPlayer();
        BowlingFrames bowlingFrames = bowlingGame.getBowlingFrames();
        List<FrameScoreResult> frameScoreResults = bowlingFrames.getFrames()
                .stream()
                .map(bowlingFrame -> FrameScoreResult.newInstance(bowlingFrame.getFrameScore()))
                .collect(Collectors.toList());

        return new BowlingGameResult(player.getName(), frameScoreResults);
    }

    public String getName() {
        return name;
    }

    public List<FrameScoreResult> getFrameScores() {
        return frameScoreResults;
    }
}
