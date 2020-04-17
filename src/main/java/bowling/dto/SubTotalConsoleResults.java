package bowling.dto;

import bowling.BowlingFrame;
import bowling.BowlingFrames;
import bowling.NextAddingUpScores;

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
        Stack<SubTotalConsoleResult> subTotals = new Stack<>();

        NextAddingUpScores nextAddingUpScores = null;
        for (int i = bowlingFrames.size() - 1; i >= 0; i--) {
            BowlingFrame bowlingFrame = bowlingFrames.getFrame(i);

            SubTotalConsoleResult subTotalConsoleResult = SubTotalConsoleResult.newInstance(bowlingFrame, nextAddingUpScores);

            nextAddingUpScores = subTotalConsoleResult.getNextAddingUpScores();

            subTotals.push(subTotalConsoleResult);
        }

        return new SubTotalConsoleResults(IntStream.range(0, subTotals.size())
                .mapToObj(i -> subTotals.pop())
                .collect(Collectors.toList()));
    }

    public SubTotalConsoleResult get(final int index) {
        return subTotalConsoleResults.get(index);
    }

    public int size() {
        return subTotalConsoleResults.size();
    }
}