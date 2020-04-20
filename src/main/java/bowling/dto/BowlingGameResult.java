package bowling.dto;

import bowling.Player;
import bowling.Score;
import bowling.frame.BowlingFrames;
import bowling.game.BowlingGame;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGameResult {

    private final String name;
    private final List<BowlingFrameConsoleResult> bowlingFrameConsoleResults;

    private BowlingGameResult(final String name, final List<BowlingFrameConsoleResult> bowlingFrameConsoleResults) {
        this.name = name;
        this.bowlingFrameConsoleResults = bowlingFrameConsoleResults;
    }

    public static BowlingGameResult newInstance(final BowlingGame bowlingGame) {
        Player player = bowlingGame.getPlayer();

        return new BowlingGameResult(player.getName(), makeBowlingFrameConsoleResults(bowlingGame.getBowlingFrames()));
    }

    private static List<BowlingFrameConsoleResult> makeBowlingFrameConsoleResults(final BowlingFrames bowlingFrames) {
        List<Score> totalScores = bowlingFrames.getTotalScores();

        return IntStream.range(0, bowlingFrames.size())
                .mapToObj(i -> BowlingFrameConsoleResult.newInstance(bowlingFrames.getFrame(i), totalScores.get(i)))
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<BowlingFrameConsoleResult> getBowlingFrameConsoleResults() {
        return bowlingFrameConsoleResults;
    }
}
