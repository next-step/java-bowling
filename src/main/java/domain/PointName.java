package domain;

import java.util.Arrays;

public enum PointName {
    ZERO(0, "-", false),
    ONE(1, "1", false),
    TWO(2, "2", false),
    THREE(3, "3", false),
    FOUR(4, "4", false),
    FIVE(5, "5", false),
    SIX(6, "6", false),
    SEVEN(7, "7", false),
    EIGHT(8, "8", false),
    NINE(9, "9", false),
    SPARE(10, "/", true),
    STRIKE(10, "X", false);

    private int point;
    private String pointName;
    private boolean isSpare;

    PointName(int point, String pointName, boolean isSpare) {
        this.point = point;
        this.pointName = pointName;
        this.isSpare = isSpare;
    }

    public static String valueOfPointName(int point, boolean isSpare) {
        return valueOf(point, isSpare).getPointName();
    }

    public static PointName valueOf(int point, boolean isSpare) {
        if (isSpare) {
            return SPARE;
        }

        return Arrays.stream(values())
                .filter(pointName -> pointName.point == point && pointName.isSpare == isSpare)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getPointName() {
        return pointName;
    }
}
