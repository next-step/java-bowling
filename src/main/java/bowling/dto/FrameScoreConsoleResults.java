package bowling.dto;

import bowling.BowlingFrame;
import bowling.BowlingFrames;

import java.util.ArrayList;
import java.util.List;

public class FrameScoreConsoleResults {

    private final List<FrameScoreConsoleResult> frameScoreConsoleResults;

    private FrameScoreConsoleResults(final List<FrameScoreConsoleResult> frameScoreConsoleResults) {
        this.frameScoreConsoleResults = frameScoreConsoleResults;
    }

    public static FrameScoreConsoleResults newInstance(final BowlingFrames bowlingFrames) {
        List<FrameScoreConsoleResult> result = new ArrayList<>();
        SubTotalConsoleResults subTotals = SubTotalConsoleResults.newInstance(bowlingFrames);
        validateSize(bowlingFrames, subTotals);

        int totalScore = 0;
        for (int i = 0; i < bowlingFrames.size(); i++) {
            BowlingFrame bowlingFrame = bowlingFrames.getFrame(i);
            TotalScoreResult totalScoreResult = TotalScoreResult.newInstance(subTotals.get(i), totalScore);
            totalScore = totalScoreResult.getTotalScore();

            result.add(FrameScoreConsoleResult.newInstance(bowlingFrame.getFrameScore(), totalScoreResult));
        }

        return new FrameScoreConsoleResults(result);
    }

    private static void validateSize(final BowlingFrames bowlingFrames, final SubTotalConsoleResults subTotalConsoleResults) {
        if (bowlingFrames.size() != subTotalConsoleResults.size()) {
            throw new IllegalStateException("bowlingFrame size must be same as subTotalConsoleResults size");
        }
    }

    public List<FrameScoreConsoleResult> getFrameScoreConsoleResults() {
        return frameScoreConsoleResults;
    }
}
