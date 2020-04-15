package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    private static final int DOUBLE_STRIKE_POINT = 20;
    private static final int STRIKE_POINT = 10;
    private static final int SPARE_POINT = 10;
    private static final int MAX_POINT_COUNT = 10;

    // 볼링 칠때마다 쌓인다
    private List<Point> points;
    private final int frameNo;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
        points = new ArrayList<>();
    }

    @Override
    public void throwBall(int fallenCount) throws IllegalArgumentException {
        Point point = new Point(fallenCount);
        if (point.isScoreable(getLeftPin())) {
            this.points.add(point);
            return;
        }
        throw new IllegalArgumentException("다시 입력해주세요(남은 핀: " + getLeftPin() + ")");
    }

    @Override
    public boolean isThrowable() {
        // 0번 또는 1번 던졌을때
        if (points.size() == 0 || points.size() == 1) {
            return true;
        }
        // 스트라이크 또는 스페어 처리됬을때
        if (isSpare() || isDoubleStrike()) {
            return true;
        }
        return false;
    }

    @Override
    public int getFrameNo() {
        return this.frameNo;
    }

    @Override
    public List<Point> getPoints() {
        return this.points;
    }


    private int getSumPoints() {
        return this.points.stream()
                .mapToInt(Point::getPoint)
                .sum();
    }

    // 남은 핀
    private int getLeftPin() {
        if (isStrike()) {
            return MAX_POINT_COUNT;
        }
        if (points.size() == 1) {
            return MAX_POINT_COUNT - getSumPoints();
        }
        return MAX_POINT_COUNT;
    }

    private boolean isStrike() {
        if (points.size() == 1 && getSumPoints() == STRIKE_POINT) {
            return true;
        }
        return false;
    }

    private boolean isDoubleStrike() {
        if (points.size() == 2 && getSumPoints() == DOUBLE_STRIKE_POINT) {
            return true;
        }
        return false;
    }

    private boolean isSpare() {
        if (points.size() == 2 && getSumPoints() == SPARE_POINT) {
            return true;
        }
        return false;
    }
}
