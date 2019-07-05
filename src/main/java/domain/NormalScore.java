package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NormalScore {
    private static final int FIRST = 0;
    public static final int STRIKE = 10;

    private final List<Point> points;

    public NormalScore() {
        this.points = new ArrayList<>();
    }

    public int bowl(int score) {
        if (points.size() == NormalFrame.NORMAL_FRAME_SIZE) {
            throw new IllegalStateException("사전 정의된 size수 만큼 투구 가능합니다.");
        }

        if (isStrike()) {
            throw new IllegalStateException("1~9번 프레임은 초구가 스트라이크면 더이상 던질 수 없습니다.");
        }

        if (isOverScore(score)) {
            throw new IllegalArgumentException("두 번의 투구 합은 10점을 초과할 수 없습니다.");
        }

        Point point = Point.bowl(score);
        points.add(point);

        return sumScore();
    }

    private boolean isOverScore(int currentScore) {
        if (points.size() < 1) {
            return false;
        }
        int lastScore = getPoint(points.size() - 1);
        return lastScore + currentScore > 10 ? true : false;
    }

    private int sumScore() {
        return points.stream()
                .mapToInt(point -> point.getPoint())
                .sum();
    }

    public boolean isStrike() {
        if (points.size() < 1) {
            return false;
        }
        return getPoint(FIRST) == STRIKE ? true : false;
    }

    public boolean isSpare() {
        if (!isStrike() && sumScore() == STRIKE) {
            return true;
        }
        return false;
    }

    private int getPoint(int position) {
        Point point = points.get(position);
        return point.getPoint();
    }

    public String getScore() {
        final String SCORE_CONNECTOR = "|";

        String score = IntStream.range(0, NormalFrame.NORMAL_FRAME_SIZE)
                .boxed()
                .map(count -> PointName.valueOfPointName(getPoint(count), isSpare()))
                .map(result -> result + SCORE_CONNECTOR)
                .collect(Collectors.joining());
        return score.substring(0, score.length() - 2);
    }
}
