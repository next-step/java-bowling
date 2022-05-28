package bowling.domain;

import java.util.Optional;

public enum HitState {
    NORMAL((frame) -> {
        return Optional.of(frame.firstScore() + frame.secondScore());
    }),

    SPARE((frame) -> {
        if (!frame.hasNext()) {
            return Optional.empty();
        }

        Frame next = frame.next();
        Optional<Integer> firstScoreOptional = next.getFirstScoreAsOptional()
            .map(Score::get);

        if (firstScoreOptional.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(firstScoreOptional.get() + 10);
    }),
    STRIKE((frame) -> {
        int totalScore = 10;
        int additionalCount = 2;
        Frame current = frame.next();

        if (current == null || current.getFirstScoreAsOptional().isEmpty()) {
            return Optional.empty();
        }

        while (current != null && additionalCount > 0) {
            if (current.getFirstScoreAsOptional().isPresent()) {
                totalScore += current.firstScore();
                --additionalCount;
            }

            if (current.getSecondScoreAsOptional().isPresent() && additionalCount > 0) {
                totalScore += current.secondScore();
                --additionalCount;
            }

            current = current.next();
        }

        if (additionalCount == 0) {
            return Optional.of(totalScore);
        }

        return Optional.empty();
    }),
    FINAL_STRIKE((frame -> {
        return Optional.empty();
    })),
    FINAL_SPARE((frame -> {
        return Optional.empty();
    }));

    private final ScoreCalculator calculator;

    public Optional<Integer> scoreOf(Frame frame) {
        if (frame instanceof FinalFrame) {

        }

        return calculator.calculate(frame);
    }

    HitState(ScoreCalculator calculator) {
        this.calculator = calculator;
    }

    public Optional<Integer> scoreOfFinal(FinalFrame finalFrame) {
        return calculator.calculate(finalFrame);
    }

}

