package bowling.domain;

import java.util.Optional;

public class Point {

    private Point before; // 현재까지 점수
    private int now; // 이번 프레임 점수
    private int bonusPoint; // 이번 프레임 보너스 점수
    private int left; // 남은 시도 횟수

    public Point(Point point, int now, int left) {
        this.before = point;
        this.now = now;
        this.left = left;
    }

    public void add(Pin pin) {
        this.bonusPoint += pin.getValue();
        this.left--;
    }

    public boolean canAddPoint() {
        return left > 0;
    }

    public int point() {
        return Optional.ofNullable(before)
                .map(Point::point)
                .orElse(0) + now + bonusPoint;
    }


}
