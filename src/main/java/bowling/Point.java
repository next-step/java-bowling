package bowling;

import java.util.Objects;

import static bowling.model.BowlingValidator.changeToInt;
import static bowling.model.BowlingValidator.isBlank;

public class Point {
    private static final int MAX_POINT = 10;
    private static final int MIN_POINT = 0;
    private static final int DEFAULT_POINT = 0;

    private final int point;

    public Point(String knockDownCount) {
        isBlank(knockDownCount);
        isDigit(knockDownCount);
        isValidRange(knockDownCount);
        this.point = changeToInt(knockDownCount);
    }

    public Point(int point) {
        this.point = point;
    }

    private void isDigit(String pinCount) {
        if (!pinCount.trim().matches("[0-9]*")) {
            throw new IllegalArgumentException("숫자를 입력해주세요");
        }
    }

    public static void isValidRange(String pinCount) {
        int knockDownCount = changeToInt(pinCount);

        if(knockDownCount < MIN_POINT || knockDownCount > MAX_POINT) {
            throw new IllegalArgumentException("핀 갯수를 1개 이상 10개 이하로 입력해주세요.");
        }
    }

    public static void isValidRange(int pinCount) {
        if(pinCount < MIN_POINT || pinCount > MAX_POINT) {
            throw new IllegalArgumentException("핀 갯수를 1개 이상 10개 이하로 입력해주세요.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point1 = (Point) o;
        return point == point1.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
