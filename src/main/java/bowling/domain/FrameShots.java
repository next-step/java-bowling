package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bowling.engine.BonusScores;
import bowling.engine.Score;
import bowling.engine.Shot;
import bowling.engine.Shots;
import bowling.engine.collection.FirstClassMutableList;

import static bowling.domain.ShotResult.STRIKE;

public class FrameShots extends FirstClassMutableList<Shot> implements Shots {
    protected FrameShots(List<Shot> collection) {
        super(collection);
    }

    static Shots of(List<Shot> shots, boolean isFinal) {
        if (shots == null) {
            throw new IllegalArgumentException("shots cannot be null");
        }

        if (isFinal ? !validateFinal(shots) : !validate(shots)) {
            throw new IllegalArgumentException("invalid score: " + shots);
        }

        return new FrameShots(shots);
    }

    public static Shots of(List<Shot> shots) {
        return of(shots, false);
    }

    public static Shots ofFinal(List<Shot> shots) {
        return of(shots, true);
    }

    public static FrameShots emptyShot() {
        return new FrameShots(Collections.emptyList());
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
        Shot nextShot = lastOptional().map(last -> ShotResult.of(last, shot))
                .orElse(shot);
        append(nextShot);

        return this;
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
    public BonusScores clearBonus() {
        return lastOptional()
                .map(this::bonusScores)
                .orElse(ClearBonusScores.byNone());
    }

    private BonusScores bonusScores(Shot shot) {
        if (shot.isSpare()) {
            return ClearBonusScores.bySpare();
        }

        if (shot.equals(STRIKE) && size() == 1) { // isStrike
            return ClearBonusScores.byStrike();
        }

        return ClearBonusScores.byNone();
    }
}
