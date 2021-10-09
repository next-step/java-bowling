package bowling.domain.score;

import bowling.domain.frame.Frame;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static bowling.common.Pin.MAX;

public class NormalScore extends BaseScore {

    private final boolean isDone;

    private NormalScore(int first, int second, int total, boolean isDone) {
        super(first, second, total);
        this.isDone = isDone;
    }

    private NormalScore(int first, int second, boolean isDone) {
        this(first, second, -1, isDone);
    }

    public static NormalScore of(int first, int second, int total, boolean isDone) {
        return new NormalScore(first, second, total, isDone);
    }

    public static NormalScore first(int score) {
        validateScore(score);
        if (isStrike(score)) {
            return new NormalScore(score, -1, true);
        }
        return new NormalScore(score, -1, false);
    }

    private static boolean isStrike(int score) {
        return score == MAX.value();
    }

    @Override
    protected int calculateWith(int base, Frame now) {

        if (!isDone) {
            return NONE;
        }

        if (neitherSpareNorStrike()) {
            return getAll().stream().filter(score -> score != NONE).reduce(base, Integer::sum);
        }

        Frame next = now.next();
        if (next == null) {
            return NONE;
        }

        return calculateWhenSpareOrStrike(base, next);
    }

    private boolean neitherSpareNorStrike() {
        return !isSpare() && !isStrike();
    }

    private int calculateWhenSpareOrStrike(int base, Frame next) {
        if (isSpare()) {
            return base + next.addWithFirstScore(sum());
        }

        return calculateWhenStrike(base, next);
    }

    private int calculateWhenStrike(int base, Frame next) {
        List<Integer> nextScores = scoresFrom(next);

        if (nextScores.size() < 2) {
            return NONE;
        }

        return base + getFirst() + nextScores.get(0) + nextScores.get(1);
    }

    private List<Integer> scoresFrom(Frame next) {

        List<Integer> nextFrameAllScores = next.getAllScores();

        List<Integer> nextNextFrameAllScores =
                Optional.ofNullable(next.next()).map(Frame::getAllScores).orElse(new ArrayList<>());

        return Stream.concat(nextFrameAllScores.stream(), nextNextFrameAllScores.stream())
                .filter(score -> score != NONE)
                .collect(Collectors.toList());
    }

    @Override
    protected void validateCombinedScores(int score) {
        if (getFirst() + score > MAX.value()) {
            throw new IllegalArgumentException("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
        }
    }

    @Override
    public List<Integer> getAll() {
        return Collections.unmodifiableList(Arrays.asList(getFirst(), getSecond()));
    }

    @Override
    public Score accumulate(int score) {
        return second(score);
    }

    private NormalScore second(int score) {
        validateScore(score);
        validateCombinedScores(score);
        return new NormalScore(getFirst(), score, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalScore that = (NormalScore) o;
        return isDone == that.isDone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDone);
    }
}
