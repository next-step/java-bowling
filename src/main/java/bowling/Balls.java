package bowling;

import java.util.ArrayList;
import java.util.List;

public class Balls {
    public static final int FIRST_BALL_INDEX = 1;
    public static final int DEFAULT_BALL_SIZE = 2;
    public static final String IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE = "한 프레임의 세번째 투구는 마지막 프레임에서 스트라이크 또는 스페어일때 가능 합니다.";
    public static final String IS_OVER_FLOW_BALL_COUNT = "마지막 프레임을 제외한 프레임에서는 최대 두번만 투구 할 수 있습니다.";
    public static final String IS_OVER_FLOW_PIN_COUNT = "마지막 프레임을 제외한 프레임에서는 10개 핀만 쓰러트릴 수 있습니다.";
    private List<Ball> balls;

    public Balls() {
        balls = new ArrayList<>();
    }

    public void add(int pin, boolean isLastFrame) {
        checkAddAble(pin, isLastFrame);
        balls.add(new Ball(pin));
    }

    private void checkAddAble(int pin, boolean isLastFrame) {
        if (isLastFrame && isThirdBall() && isNotAddAbleThirdBall()) {
            throw new IllegalArgumentException(IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE);
        }

        if (!isLastFrame && isThirdBall()) {
            throw new IllegalArgumentException(IS_OVER_FLOW_BALL_COUNT);
        }

        if (!isLastFrame && isOverFlowPinCount(pin)) {
            throw new IllegalArgumentException(IS_OVER_FLOW_PIN_COUNT);
        }
    }

    private boolean isThirdBall() {
        return balls.size() == DEFAULT_BALL_SIZE;
    }

    private boolean isNotAddAbleThirdBall() {
        return !isAddAbleThirdBall();
    }

    private boolean isAddAbleThirdBall() {
        return isStrike() || isSpare();
    }

    private boolean isOverFlowPinCount(int pin) {
        return score() + pin > Ball.ALL_PIN_COUNT;
    }

    public boolean isStrike() {
        return balls.stream()
                .limit(FIRST_BALL_INDEX)
                .map(Ball::isStrike)
                .findFirst()
                .orElse(false);
    }

    public boolean isSpare() {
        int sum = balls.stream()
                .limit(DEFAULT_BALL_SIZE)
                .mapToInt(Ball::getPin)
                .sum();
        return sum == Ball.ALL_PIN_COUNT;
    }

    public int score() {
        return balls.stream()
                .mapToInt(Ball::getPin)
                .sum();
    }

    public int addAblePinCount() {
        if (isThirdBall() && isAddAbleThirdBall()) {
            return Ball.ALL_PIN_COUNT;
        }

        return Ball.ALL_PIN_COUNT - score();
    }
}
