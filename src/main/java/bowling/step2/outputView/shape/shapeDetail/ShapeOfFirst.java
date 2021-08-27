package bowling.step2.outputView.shape.shapeDetail;

import bowling.step2.domain.Count;

import java.util.Arrays;

public enum ShapeOfFirst {
    ZERO(Count.ZERO, "-"),
    ONE(Count.ONE, "1"),
    TWO(Count.TWO, "2"),
    THREE(Count.THREE, "3"),
    FOUR(Count.FOUR, "4"),
    FIVE(Count.FIVE, "5"),
    SIX(Count.SIX, "6"),
    SEVEN(Count.SEVEN, "7"),
    EIGHT(Count.EIGHT, "8"),
    NINE(Count.NINE, "9"),
    STRIKE(Count.TEN, "X"),
    SPARE(Count.TEN, "X"),
    NONE(Count.NONE, " ");

    private final Count count;
    private final String shape;

    ShapeOfFirst(Count count, String shape) {
        this.count = count;
        this.shape = shape;
    }

    public static ShapeOfFirst of(Count count) {
        return Arrays.stream(values())
                .filter(shapeOfFirst -> shapeOfFirst.count == count)
                .findFirst()
                .orElse(ShapeOfFirst.NONE);
    }

    @Override
    public String toString() {
        return shape;
    }
}
