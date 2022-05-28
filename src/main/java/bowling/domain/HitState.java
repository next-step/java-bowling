package bowling.domain;

import java.util.Optional;

public enum HitState {
    NORMAL(frame -> Optional.of(frame.firstScore() + frame.secondScore())),
    SPARE(frame -> {
        if (!frame.hasNext()) {
            return Optional.empty();
        }

        Frame next = frame.next();

        if (next.isNotThrowFirstYet()) {
            return Optional.empty();
        }
        return Optional.of(next.firstScore() + 10);
    }),
    STRIKE(frame -> {
        Frame current = frame.next();
        if (current == null || current.isNotThrowFirstYet()) {
            return Optional.empty();
        }

        TotalScore totalScore = TotalScore.ofInitialStrike();
        BonusShotCount bonusCount = BonusShotCount.ofStrike();
        while (current != null && bonusCount.isRemained()) {
            totalScore.appendScoreOf(bonusCount, current);
            current = current.next();
        }

        return resultScoreAsOptional(totalScore, bonusCount);
    });

    private static Optional<Integer> resultScoreAsOptional(TotalScore totalScore, BonusShotCount count) {
        if (count.isRemained()) {
            return Optional.empty();
        }
        return totalScore.getAsOptional();
    }

    private final ScoreCalculator calculator;

    public Optional<Integer> scoreOf(Frame frame) {
        return calculator.calculate(frame);
    }

    HitState(ScoreCalculator calculator) {
        this.calculator = calculator;
    }

}

