package bowling.domain.shot.type;

import bowling.domain.shot.Pins;

import java.util.Arrays;

public enum ShotType {
    STRIKE(new ClearProvider(2), new StrikeChecker()),
    SPARE(new ClearProvider(1), new SpareChecker()),
    MISS_FIRST(new MissProvider(true), new MissChecker(true)),
    MISS_SECOND(new MissProvider(false), new MissChecker(false)),
    GUTTER_FIRST(new MissProvider(true), new GutterChecker(true)),
    GUTTER_SECOND(new MissProvider(false), new GutterChecker(false));

    private final StatusProvider statusProvider;
    private final TypeChecker typeChecker;

    ShotType(StatusProvider statusProvider, TypeChecker typeChecker) {
        this.statusProvider = statusProvider;
        this.typeChecker = typeChecker;
    }

    public boolean isFinished() {
        return statusProvider.isFinished();
    }

    public boolean isCleared() {
        return statusProvider.isClear();
    }

    public int getBonusCount() {
        return statusProvider.getBonusCount();
    }

    public static ShotType of(Pins firstPins) {
        return Arrays.stream(values())
                .filter(v -> v.typeChecker.availableFirstShots(firstPins))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not Matched Instance : firstPins=%s", firstPins)));
    }

    public static ShotType of(Pins firstPins, Pins secondPins) {
        return Arrays.stream(values())
                .filter(v -> v.typeChecker.availableSecondShots(firstPins, secondPins))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not Matched Instance : firstPins=%s, secondPins=%s", firstPins, secondPins)));
    }
}
