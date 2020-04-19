package bowling.dto;

import bowling.BowlingGame;
import bowling.Player;
import bowling.Score;
import bowling.frame.BowlingFrame;
import bowling.frame.BowlingFrames;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameResult {

    private final String name;
    private final List<BowlingFrameConsoleResult> bowlingFrameConsoleResults;

    private BowlingGameResult(final String name, final List<BowlingFrameConsoleResult> bowlingFrameConsoleResults) {
        this.name = name;
        this.bowlingFrameConsoleResults = bowlingFrameConsoleResults;
    }

    public static BowlingGameResult newInstance(final BowlingGame bowlingGame) {
        Player player = bowlingGame.getPlayer();
        List<BowlingFrameConsoleResult> bowlingFrameConsoleResults = getBowlingFrameConsoleResults(bowlingGame);

        return new BowlingGameResult(player.getName(), bowlingFrameConsoleResults);
    }

    private static List<BowlingFrameConsoleResult> getBowlingFrameConsoleResults(BowlingGame bowlingGame) {
        BowlingFrames bowlingFrames = bowlingGame.getBowlingFrames();
        int totalScore = 0;

        List<BowlingFrameConsoleResult> bowlingFrameConsoleResults = new ArrayList<>();
        for(BowlingFrame bowlingFrame : bowlingFrames.getFrames()) {
            BowlingFrameConsoleResult bowlingFrameConsoleResult = BowlingFrameConsoleResult.newInstance(bowlingFrame, totalScore);
            bowlingFrameConsoleResults.add(bowlingFrameConsoleResult);
            totalScore = extractTotalScore(bowlingFrame, totalScore);
        }

        return bowlingFrameConsoleResults;
    }

    private static int extractTotalScore(final BowlingFrame bowlingFrame, final int beforeTotalScore) {
        Score score = bowlingFrame.getFrameScore();
        return beforeTotalScore + score.getScore();
    }

    public String getName() {
        return name;
    }

    public List<BowlingFrameConsoleResult> getBowlingFrameConsoleResults() {
        return bowlingFrameConsoleResults;
    }
}
