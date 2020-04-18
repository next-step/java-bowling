package bowling.dto;

import bowling.BowlingGame;
import bowling.Player;

import java.util.List;

public class BowlingGameResult {

    private final String name;
    private final FrameScoreConsoleResults frameScoreConsoleResults;

    private BowlingGameResult(final String name, final FrameScoreConsoleResults frameScoreConsoleResults) {
        this.name = name;
        this.frameScoreConsoleResults = frameScoreConsoleResults;
    }

    public static BowlingGameResult newInstance(final BowlingGame bowlingGame) {
        Player player = bowlingGame.getPlayer();
        BowlingFrames bowlingFrames = bowlingGame.getBowlingFrames();

        return new BowlingGameResult(player.getName(), FrameScoreConsoleResults.newInstance(bowlingFrames));
    }

    public String getName() {
        return name;
    }

    public List<FrameScoreConsoleResult> getFrameScores() {
        return frameScoreConsoleResults.getFrameScoreConsoleResults();
    }
}
