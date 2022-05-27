package bowling.domain;

public enum HitState {
    NORMAL((frame) -> {

        return 0;
    }),
    STRIKE((frame) -> {

        return 0;
    }),
    SPARE((frame) -> {

        return 0;
    });

    private final ScoreCalculator calculator;

    HitState(ScoreCalculator calculator) {
        this.calculator = calculator;
    }

}

