package bowling.step2.outputView;

import bowling.step2.domain.Count;
import bowling.step2.domain.Frame;
import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PitchResult {
    STRIKE(Count.TEN, "X"),
    SPARE(Count.TEN, "/"),
    ONE(Count.ONE, "1"),
    TWO(Count.TWO, "2"),
    THREE(Count.THREE, "3"),
    FOUR(Count.FOUR, "4"),
    FIVE(Count.FIVE, "5"),
    SIX(Count.SIX, "6"),
    SEVEN(Count.SEVEN, "7"),
    EIGHT(Count.EIGHT, "8"),
    NINE(Count.NINE, "9"),
    GUTTER(Count.ZERO, "-"),
    NONE(Count.NONE, " ");

    private final Count count;
    private final String shape;

    PitchResult(Count count, String shape) {
        this.count = count;
        this.shape = shape;
    }

    public static List<PitchResult> findResultOf(Frame frame) {
        if (frame instanceof NormalFrame) {
            return findResultOfNormal((NormalFrame) frame);
        }

        return findResultOfLast((LastFrame) frame);
    }

    private static List<PitchResult> findResultOfNormal(NormalFrame normalFrame) {
        List<PitchResult> results = new ArrayList<>();
        addFirstResult(results, normalFrame);
        addSecondResult(results, normalFrame);
        return results;
    }

    private static List<PitchResult> findResultOfLast(LastFrame lastFrame) {
        List<PitchResult> results = new ArrayList<>();
        addFirstResult(results, lastFrame);
        addSecondResult(results, lastFrame);
        addAdditionalResult(results, lastFrame);
        return results;
    }

    private static void addFirstResult(List<PitchResult> results, Frame frame) {
        results.add(PitchResult.findByCount(frame.countOfFirst(), 0));
    }

    private static void addSecondResult(List<PitchResult> results, Frame frame) {
        if (frame instanceof NormalFrame) {
            results.add(PitchResult.findByCount(frame.countOfFirst().sum(frame.countOfSecond()), frame.countOfFirst() == Count.TEN ? 0 : 1));
            return;
        }

        if (frame.countOfSecond() == Count.NONE) {
            results.add(PitchResult.findByCount(frame.countOfSecond(), 0));
            return;
        }

        if (frame.countOfFirst() == Count.TEN) {
            results.add(PitchResult.findByCount(frame.countOfSecond(), 0));
            return;
        }

        results.add(PitchResult.findByCount(frame.countOfFirst().sum(frame.countOfSecond()), 1));
    }

    private static void addAdditionalResult(List<PitchResult> results, LastFrame frame) {
        results.add(PitchResult.findByCount(frame.countOfAdditional(), 0));
    }

    private static PitchResult findByCount(Count count, int skip) {
        return Arrays.stream(values())
                .skip(skip)
                .filter(pitchResult -> pitchResult.count.equals(count))
                .findFirst()
                .orElse(PitchResult.NONE);
    }
}
