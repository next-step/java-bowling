package bowling.domain;

import java.util.Optional;

public enum HitState {
    NORMAL(0, (frame) -> {
        return Optional.empty();
//        frame.getFirstScoreAsOptional()
//            .orElse()
//        return frame.getFirstScoreAsOptional().get() + frame.getSecondScoreAsOptional().get();
    }),

    SPARE(1, (frame) -> {
        return Optional.empty();
//        final int spare = 10;
//
//        if (!frame.hasNext()) {
//            return 0; // or throw
//        }
//
//        Frame next = frame.next();
//        Optional<Score> optionalScore = next.getFirstScoreAsOptional();
//
//        return optionalScore.map(score -> spare + score.get())
//            .orElse(0); // or throw?
    }),
    STRIKE(2, (frame) -> {
        return Optional.empty();
//        final int strike = 10;
//
//        if (!frame.hasNext()) {
//            return 0; // or throw?
//        }
//
//        Frame next = frame.next();
//        Optional<Score> optionalScore = next.getFirstScoreAsOptional();
//
//        return optionalScore.map(score -> strike + score.get())
//            .orElse(0); // or throw?
    });

    private final int nextAddScore;
    private final ScoreCalculator calculator;

    public Optional<Integer> scoreOf(Frame frame) {
        return calculator.calculate(frame);
    }

    HitState(int nextAddScore, ScoreCalculator calculator) {
        this.nextAddScore = nextAddScore;
        this.calculator = calculator;
    }

}

