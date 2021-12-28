package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.domain.Ball.MAX_PIN_COUNT;
import static java.util.stream.Collectors.joining;

public class Balls {
    protected final List<Ball> balls;

    protected Balls() {
        balls = new ArrayList<>(Collections.singletonList(Ball.first()));
    }

    public static Balls init() {
        return new Balls();
    }

    private int getLastIndex() {
        return balls.size() - 1;
    }

    public Ball getLast() {
        return balls.get(getLastIndex());
    }

    public int total() {
        return balls.stream().map(Ball::getFallenPinCount).reduce(Integer::sum).orElse(0);
    }

    public String symbol() {
        return balls.stream().filter(Ball::isNotReady).map(Ball::symbol).collect(joining("|"));
    }

    public Balls bowl(Ball ball) {
        balls.add(getLast().bowl(ball));
        if (total() > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("1~9 프레임에서 쓰러트릴 수 있는 볼링핀의 총 갯수는 10을 초과할 수 없습니다.");
        }
        return this;
    }

    public boolean isEnd() {
        return getLast().isEnd();
    }

    public Ball getBeforeLast() {
        if (getLastIndex() == 0) {
            return Ball.first();
        }
        return balls.get(getLastIndex() - 1);
    }

    public Score score() {
        return getLast().score().toScore(total());
    }

    public Score score(Score previous) {
        Score score = previous;
        for (Ball ball : balls) {
            if (ball.isNotReady()) {
                score = ball.score(score);
            }
            if (score.canCalculate()) {
                return score;
            }
        }
        return score;
    }
}
