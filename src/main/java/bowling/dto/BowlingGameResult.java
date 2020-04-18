package bowling.dto;

import bowling.BowlingGame;
import bowling.Player;
import bowling.frame.BowlingFrames;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameResult {

    private final String name;
    private final List<BowlingFrameConsoleResult> bowlingFrameConsoleResults;

    private BowlingGameResult(final String name, final List<BowlingFrameConsoleResult> bowlingFrameConsoleResults) {
        this.name = name;
        this.bowlingFrameConsoleResults = bowlingFrameConsoleResults;
    }

    public static BowlingGameResult newInstance(final BowlingGame bowlingGame) {
        Player player = bowlingGame.getPlayer();
        BowlingFrames bowlingFrames = bowlingGame.getBowlingFrames();

        List<BowlingFrameConsoleResult> bowlingFrameConsoleResults = bowlingFrames.getFrames()
                .stream()
                .map(BowlingFrameConsoleResult::newInstance)
                .collect(Collectors.toList());

        return new BowlingGameResult(player.getName(), bowlingFrameConsoleResults);
    }

    public String getName() {
        return name;
    }

    public List<BowlingFrameConsoleResult> getBowlingFrameConsoleResults() {
        return bowlingFrameConsoleResults;
    }
}
