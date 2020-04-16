package bowling.dto;

import bowling.*;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        Stack<FrameScoreConsoleResult> frameScoreConsoleResults = new Stack<>();

        NextAddingUpScores nextAddingUpScores = null;
        for (int i = bowlingFrames.size() - 1; i >= 0; i--) {
            BowlingFrame bowlingFrame = bowlingFrames.getFrame(i);

            FrameScoreConsoleResult frameScoreConsoleResult = makeFrameScoreConsoleResult(bowlingFrame, nextAddingUpScores);
            nextAddingUpScores = frameScoreConsoleResult.getNextAddingUpScores();

            frameScoreConsoleResults.push(frameScoreConsoleResult);
        }

        return IntStream.range(0, frameScoreConsoleResults.size())
                .mapToObj(i -> frameScoreConsoleResults.pop())
                .collect(Collectors.toList());
    }

    private static FrameScoreConsoleResult makeFrameScoreConsoleResult(final BowlingFrame bowlingFrame, final NextAddingUpScores nextAddingUpScores) {
        return FrameScoreConsoleResult.newInstance(bowlingFrame, nextAddingUpScores);
    }

    public String getName() {
        return name;
    }

    public List<FrameScoreConsoleResult> getFrameScores() {
        return frameScoreConsoleResults;
    }
}
