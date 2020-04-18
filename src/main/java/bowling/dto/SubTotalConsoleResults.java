package bowling.dto;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubTotalConsoleResults {

    private final List<SubTotalConsoleResult> subTotalConsoleResults;

    private SubTotalConsoleResults(final List<SubTotalConsoleResult> subTotalConsoleResults) {
        this.subTotalConsoleResults = subTotalConsoleResults;
    }

    public static SubTotalConsoleResults newInstance(final BowlingFrames bowlingFrames) {
        Stack<SubTotalConsoleResult> subTotals = makeReverseSubTotalConsoleResults(bowlingFrames);

        return new SubTotalConsoleResults(IntStream.range(0, subTotals.size())
                .mapToObj(i -> subTotals.pop())
                .collect(Collectors.toList()));
    }

    private static Stack<SubTotalConsoleResult> makeReverseSubTotalConsoleResults(BowlingFrames bowlingFrames) {
        Stack<SubTotalConsoleResult> subTotals = new Stack<>();

        NextAddingUpScores nextAddingUpScores = null;
        for (int i = bowlingFrames.size() - 1; i >= 0; i--) {
            BowlingFrame bowlingFrame = bowlingFrames.getFrame(i);

            SubTotalConsoleResult subTotalConsoleResult = SubTotalConsoleResult.newInstance(bowlingFrame, nextAddingUpScores);

            nextAddingUpScores = subTotalConsoleResult.getNextAddingUpScores();

            subTotals.push(subTotalConsoleResult);
        }
        return subTotals;
    }

    public SubTotalConsoleResult get(final int index) {
        return subTotalConsoleResults.get(index);
    }

    public int size() {
        return subTotalConsoleResults.size();
    }
}