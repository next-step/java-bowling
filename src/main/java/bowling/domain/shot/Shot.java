package bowling.domain.shot;

import bowling.domain.shot.type.ShotType;

import java.util.Objects;

public class Shot {
    private final Pins pins;
    private final ShotType shotType;

    protected Shot(Pins pins, ShotType shotType) {
        this.pins = pins;
        this.shotType = shotType;
    }

    public static Shot init(int shotScore) {
        Pins pins = Pins.of(shotScore);
        return new Shot(pins, ShotType.of(pins));
    }

    public Shot next(int next) {
        if (!shotType.isFinished()) {
            Pins nextPins = Pins.of(next);
            return new Shot(nextPins, ShotType.of(pins, nextPins));
        }

        return init(next);
    }

    public boolean isClear() {
        return shotType.isCleared();
    }

    public ShotType scoreType() {
        return shotType;
    }

    public int singleScore() {
        return pins.score();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shot shot = (Shot) o;
        return pins.equals(shot.pins) &&
                shotType.equals(shot.shotType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, shotType);
    }

    @Override
    public String toString() {
        return "ShotScore{" +
                "score=" + pins +
                ", scoreType=" + shotType +
                '}';
    }
}
