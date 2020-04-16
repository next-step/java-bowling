package bowling.dto;

import bowling.*;

import java.util.ArrayList;
import java.util.List;

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

        return new BowlingGameResult(player.getName(), makeFrameScoreConsoleResults(bowlingFrames));
    }

    private static List<FrameScoreConsoleResult> makeFrameScoreConsoleResults(final BowlingFrames bowlingFrames) {
        List<FrameScoreConsoleResult> frameScoreConsoleResults = new ArrayList<>();

        NextAddingUpScores nextAddingUpScores = null;
        for (int i = bowlingFrames.size() - 1; i >= 0; i--) {
            BowlingFrame bowlingFrame = bowlingFrames.getFrame(i);

            FrameScore frameScore = bowlingFrame.getFrameScore();
            SubTotal subTotal = bowlingFrame.getSubTotal(nextAddingUpScores);
            frameScoreConsoleResults.add(FrameScoreConsoleResult.newInstance(frameScore, subTotal));

            nextAddingUpScores = subTotal.getNextAddingUpScores();
        }

        return frameScoreConsoleResults;
    }

    public String getName() {
        return name;
    }

    public List<FrameScoreConsoleResult> getFrameScores() {
        return frameScoreConsoleResults;
    }
}
