package bowling.domain;

import java.util.Optional;

public class Point {

    private Point before;
    private int now; // 이번 프레임 점수 + 보너스 점수

    public Point(Point before) {
        this.before = before;
        this.now = 0;
    }

    public void add(Pin pin) {
        this.now += pin.getValue();
    }

    public Integer point() {
        return Optional.ofNullable(before)
                .map(Point::point)
                .orElse(0)
                + now;
    }

    public static Point start() {
        return new Point(null);
    }


}
