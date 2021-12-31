package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.engine.Score;
import bowling.engine.Shot;
import bowling.engine.Shots;
import bowling.engine.collection.FirstClassImmutableList;

import static bowling.domain.ShotResult.STRIKE;

public class FrameShots extends FirstClassImmutableList<Shot> implements Shots {
    public static final FrameShots EMPTY_SHOT = new FrameShots(Collections.emptyList(), false);

    private final boolean isFinal;

    protected FrameShots(List<Shot> collection, boolean isFinal) {
        super(collection);
        this.isFinal = isFinal;
    }

    static Shots of(List<Shot> shots, boolean isFinal) {
        if (shots == null) {
            throw new IllegalArgumentException("shots cannot be null");
        }

        if (isFinal ? !validateFinal(shots) : !validate(shots)) {
            throw new IllegalArgumentException("invalid score: " + shots);
        }

        return new FrameShots(shots, isFinal);
    }

    public static Shots of(List<Shot> shots) {
        return of(shots, false);
    }

    public static Shots ofFinal(List<Shot> shots) {
        return of(shots, true);
    }

    static int sum(Stream<Shot> shotStream) {
        return shotStream.map(Shot::toInt)
                .reduce(0, Integer::sum);
    }

    private static boolean validate(List<Shot> shots) {
        return shots.size() <= NUMBER_OF_SHOT && sum(shots.stream()) <= NUMBER_OF_PINS;
    }

    static boolean validateFinal(List<Shot> shots) {
        return hasSpare(shots)
                || hasStrikeOrNoThirdShot(shots)
                && sumWithoutStrike(shots) <= NUMBER_OF_PINS;
    }

    private static boolean hasStrikeOrNoThirdShot(List<Shot> shots) {
        return hasStrike(shots) || shots.size() <= NUMBER_OF_SHOT;
    }

    private static boolean hasSpare(List<Shot> shots) {
        return shots.stream()
                .anyMatch(Shot::isSpare);
    }

    private static boolean hasStrike(List<Shot> shots) {
        return shots.stream()
                .anyMatch(STRIKE::equals);
    }

    private static int sumWithoutStrike(List<Shot> shots) {
        return sum(shots.stream()
                .filter(STRIKE::notEquals));
    }

    @Override
    public Shots nextShot(Shot shot) {
        Shot nextShot = isSpareChallenge() ? ShotResult.of(last(), shot) : shot;
        List<Shot> nextShots = append(nextShot).collect();

        return of(nextShots, isFinal);
    }

    @Override
    public Score score() {
        return FrameScore.of(sum(stream()));
    }

    @Override
    public boolean isClear() {
        return sum(stream()) == NUMBER_OF_PINS;
    }

    @Override
    public boolean isSpareChallenge() {
        // todo refactor: 이 메서드를 만든건 리스트가 비었을때 last()가 실패해서이다. 나머지는 ShotResult 안에서 하는게 맞다
        return size() != 0 && last() != STRIKE && !last().isSpare();
    }
}
