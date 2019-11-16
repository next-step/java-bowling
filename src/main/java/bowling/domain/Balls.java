package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Balls {
    public static final int FIRST_BALL_LIMIT_INDEX = 1;
    public static final int SECOND_BALL_INDEX = 1;
    public static final int DEFAULT_BALL_SIZE = 2;
    public static final int NOT_ADD_ABLE_PINT_COUNT = 0;
    public static final String IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE = "한 프레임의 세번째 투구는 마지막 프레임에서 스트라이크 또는 스페어일때 가능 합니다.";
    public static final String IS_OVER_FLOW_BALL_COUNT = "마지막 프레임을 제외한 프레임에서는 최대 두번만 투구 할 수 있습니다.";
    public static final String IS_OVER_FLOW_PIN_COUNT = "마지막 프레임을 제외한 프레임에서는 10개 핀만 쓰러트릴 수 있습니다.";
    private static final String RESULT_DELIMITER = "|";
    private static final String SPARE_TEXT = "/";
    private List<Ball> balls;
    private boolean isLastFrame;

    public Balls(boolean lastFrame) {
        balls = new ArrayList<>();
        this.isLastFrame = lastFrame;
    }

    public void add(int pin) {
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
                .limit(FIRST_BALL_LIMIT_INDEX)
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
        if (isLastFrame && isAddAbleAllPins()) {
            return Ball.ALL_PIN_COUNT;
        }

        if (balls.size() == DEFAULT_BALL_SIZE) {
            return NOT_ADD_ABLE_PINT_COUNT;
        }

        return Ball.ALL_PIN_COUNT - score();
    }

    private boolean isAddAbleAllPins() {
        return (balls.size() < DEFAULT_BALL_SIZE && isStrike()) ||
                (balls.size() == DEFAULT_BALL_SIZE && isAddAbleThirdBall());
    }

    public String getResult() {
        return IntStream.range(0, balls.size())
                .mapToObj(this::getBallText)
                .collect(Collectors.joining(RESULT_DELIMITER));
    }

    private String getBallText(int i) {
        if (i == SECOND_BALL_INDEX && isSpare()) {
            return SPARE_TEXT;
        }
        return balls.get(i).toString();
    }
}
