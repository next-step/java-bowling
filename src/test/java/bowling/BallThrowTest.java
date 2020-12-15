package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;

/**
 * #### 투구
 * [x] 한 투구는 0..10의 쓰러뜨린 핀 수를 가진다
 * [x] 한 프레임내의 두번째 투구는 첫번재 투구의 핀수를 더해서 10을 초과할 수 없다
 * * 마지막 프레임의 두번째 투구는 첫번째 투구가 10개의 핀수를 가진다면 또다시 10개의 핀수를 가질 수 있다
 * * 마지막 프레임의 두번째 투구는 첫번째 투구가 10개 미만의 핀수를 가진다면 첫번째 투구의 핀수를 더해서 10을 초과할 수 없다
 * * 마지막 프레임의 세번째 투구는 첫번째 투구의 핀수가 10인경우에만 존재한다
 * * 마지막 프레임의 세번째 투구는 두번째 투구가 10개의 핀수를 가진다면 또다시 10개의 투구를 가질 수 있다.
 * * 마지막 프레임의 세번째 투구는 두번째 투구가 10개 미만의 핀수를 가진다면 두번째 투구의 핀수를 더해서 10을 초과할 수 없다
 */
public class BallThrowTest {
    @Test
    void createAndEquals() {
        assertThat(new BallThrow(0)).isEqualTo(new BallThrow(0));
    }

    @DisplayName("10개 초과의 핀을 쓰러뜨릴 수 없다")
    @Test
    void fallingPinsOver10() {
        assertThatCode(() -> new BallThrow(11))
                .isInstanceOf(IllegalFallingPinsException.class);
    }

    @DisplayName("0개 미만의 핀을 쓰러뜨릴 수 없다")
    @Test
    void fallingPinsUnder0() {
        assertThatCode(() -> new BallThrow(-1))
                .isInstanceOf(IllegalFallingPinsException.class);
    }

    @DisplayName("두번째 투구는 첫번째 투구에서 쓰러뜨린 핀수를 포함하여 10을 넘을 수 없다")
    @Test
    void secondThrowOver10() {
        assertThatCode(() -> new BallThrow(5).throwSecond(6))
                .isInstanceOf(IllegalFallingPinsException.class);
    }

    @DisplayName("두번째 투구를 할 수 있다")
    @Test
    void secondThrow() {
        assertThat(new BallThrow(5).throwSecond(2)).isEqualTo(new BallThrow(2));
    }

    @DisplayName("첫번째 투구에서 10개를 쓰러뜨리면 두번째 투구는 실행 할 수 없다")
    @Test
    void illegalSecondThrow() {
        assertThatThrownBy(() -> new BallThrow(10).throwSecond(0))
                .isInstanceOf(IllegalBallThrownException.class);
    }

    private static class BallThrow {
        public static final int MAX_PINS = 10;
        public static final int MIN_FINS = 0;
        private final int fallingPins;

        public BallThrow(int fallingPins) {
            this.fallingPins = fallingPins;
            if (fallingPins > MAX_PINS || fallingPins < MIN_FINS) {
                throw new IllegalFallingPinsException();
            }
        }

        public BallThrow throwSecond(int secondFallingPins) {
            if (this.fallingPins + secondFallingPins > MAX_PINS) {
                throw new IllegalFallingPinsException();
            }
            return new BallThrow(secondFallingPins);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            BallThrow ballThrow = (BallThrow) o;
            return fallingPins == ballThrow.fallingPins;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fallingPins);
        }
    }

    private static class IllegalFallingPinsException extends RuntimeException {
    }
}
