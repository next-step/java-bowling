package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Balls {
    public static final int FIRST_BALL_LIMIT_INDEX = 1;
    public static final int SECOND_BALL_INDEX = 1;
    public static final int DEFAULT_BALL_SIZE = 2;
    public static final int LAST_FRAME_BALL_SIZE = 3;
    public static final int NOT_ADD_ABLE_PINT_COUNT = 0;
    public static final String IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE = "한 프레임의 세번째 투구는 마지막 프레임에서 스트라이크 또는 스페어일때 가능 합니다.";
    public static final String IS_OVER_FLOW_BALL_COUNT = "마지막 프레임을 제외한 프레임에서는 최대 두번만 투구 할 수 있습니다.";
    public static final String IS_OVER_FLOW_PIN_COUNT = "마지막 프레임을 제외한 프레임에서는 10개 핀만 쓰러트릴 수 있습니다.";
    private static final String RESULT_DELIMITER = "|";
    private static final String SPARE_TEXT = "/";
    private List<Ball> balls;

    public Balls(List<Ball> balls) {
        this.balls = balls;
    }

    public Balls(boolean isLastFrame) {
        balls = new ArrayList<>();
        int size = isLastFrame ? LAST_FRAME_BALL_SIZE : DEFAULT_BALL_SIZE;
        for (int i = 0; i < size; i++) {
            balls.add(new Ball());
        }
    }

    public void fallDown(int pin) {
        checkAddAble(pin);
        Ball nextBall = this.balls.stream()
                .filter(Ball::isNotFallDown)
                .findFirst()
                .orElseThrow(RuntimeException::new);
        nextBall.fallDown(pin);
    }

    private void checkAddAble(int pin) {
        boolean isLastFrame = isLastFrame();
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

    private boolean isLastFrame() {
        return this.balls.size() == LAST_FRAME_BALL_SIZE;
    }

    private boolean isThirdBall() {
        return fallDownSize() == DEFAULT_BALL_SIZE;
    }

    private boolean isNotAddAbleThirdBall() {
        return !isAddAbleThirdBall();
    }

    private boolean isAddAbleThirdBall() {
        return isStrike() || isSpare();
    }

    private boolean isOverFlowPinCount(int pin) {
        return Ball.isOverFlowPinCount(score() + pin);
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
                .filter(Ball::isFallDown)
                .mapToInt(Ball::getPin)
                .sum();
    }

    public int addAblePinCount(boolean isLastFrame) {
        if (isLastFrame && isAddAbleAllPins()) {
            return Ball.ALL_PIN_COUNT;
        }

        if (fallDownSize() == DEFAULT_BALL_SIZE) {
            return NOT_ADD_ABLE_PINT_COUNT;
        }

        return Ball.leftPinCount(score());
    }

    private boolean isAddAbleAllPins() {
        long ballsSize = fallDownSize();
        return (ballsSize < DEFAULT_BALL_SIZE && isStrike()) ||
                (ballsSize == DEFAULT_BALL_SIZE && isAddAbleThirdBall());
    }

    private long fallDownSize() {
        return balls.stream()
                .filter(Ball::isFallDown)
                .count();
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
