package bowling.domain.scoreType;

import bowling.domain.Score;

import java.util.Arrays;

public enum ScoreType {
    STRIKE(new ClearProvider(2), new StrikeChecker()),
    SPARE(new ClearProvider(1), new SpareChecker()),
    MISS_FIRST(new MissProvider(true), new MissChecker(true)),
    MISS_SECOND(new MissProvider(false), new MissChecker(false)),
    GUTTER_FIRST(new MissProvider(true), new GutterChecker(true)),
    GUTTER_SECOND(new MissProvider(false), new GutterChecker(false));

    private final StatusProvider statusProvider;
    private final TypeChecker typeChecker;

    ScoreType(StatusProvider statusProvider, TypeChecker typeChecker) {
        this.statusProvider = statusProvider;
        this.typeChecker = typeChecker;
    }

    public boolean isFinished() {
        return statusProvider.isFinished();
    }

    public boolean isCleared() {
        return statusProvider.isClear();
    }

    public int getBonusCount(){
        return statusProvider.getBonusCount();
    }

    public static ScoreType of(Score firstScore) {
        return Arrays.stream(values())
                .filter(v -> v.typeChecker.availableFirstShots(firstScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not Matched Instance : firstScore=%s", firstScore)));
    }

    public static ScoreType of(Score firstScore, Score secondScore) {
        return Arrays.stream(values())
                .filter(v -> v.typeChecker.availableSecondShots(firstScore, secondScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not Matched Instance : firstScore=%s, secondScore=%s", firstScore, secondScore)));
    }
}
