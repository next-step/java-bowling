package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * #### 투구
 * [x] 한 투구는 0..10의 쓰러뜨린 핀 수를 가진다
 * [x] 한 프레임내의 두번째 투구는 첫번재 투구의 핀수를 더해서 10을 초과할 수 없다
 * [x] 마지막 프레임의 두번째 투구는 첫번째 투구가 10개의 핀수를 가진다면 또다시 10개의 핀수를 가질 수 있다
 * [x] 마지막 프레임의 두번째 투구는 첫번째 투구가 10개 미만의 핀수를 가진다면 첫번째 투구의 핀수를 더해서 10을 초과할 수 없다
 * [x] 마지막 프레임의 세번째 투구는 첫번째 투구의 핀수가 10인경우에만 존재한다
 * [x] 마지막 프레임의 세번째 투구는 두번째 투구가 10개의 핀수를 가진다면 또다시 10개의 투구를 가질 수 있다.
 * [x] 마지막 프레임의 세번째 투구는 두번째 투구가 10개 미만의 핀수를 가진다면 두번째 투구의 핀수를 더해서 10을 초과할 수 없다
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

    @DisplayName("마지막 프레임의 두번째 투구는 첫번째 투구가 10개의 핀수를 가진다면 또다시 10개의 핀수를 가질 수 있다")
    @Test
    void lastFrameSecondThrowCouldHave10PinsWhenFirstThrowEquals10() {
        assertThat(new BallThrow(10, true).throwSecond(10)).isEqualTo(new BallThrow(10));
    }

    @DisplayName("마지막 프레임의 두번째 투구는 첫번째 투구가 10개 미만의 핀수를 가진다면 첫번째 투구의 핀수를 더해서 10을 초과할 수 없다")
    @Test
    void lastFrameSecondThrowShouldUnder10PinsWhenFirstThrowUnder10() {
        assertThatCode(() -> new BallThrow(9, true).throwSecond(2))
                .isInstanceOf(IllegalFallingPinsException.class);
    }

    @DisplayName("마지막 프레임의 세번째 투구를 던질 수 있다")
    @Test
    void lastFrameThirdThrow() {
        BallThrow first = new BallThrow(10, true);
        BallThrow second = first.throwSecond(2);
        assertThat(second.throwThird(3, first)).isEqualTo(new BallThrow(3));
    }

    @DisplayName("마지막 프레임이 아니면 세번째 투구를 던질 수 없다")
    @Test
    void nonLastFrameCannotThirdThrow() {
        BallThrow first = new BallThrow(2, false);
        BallThrow second = first.throwSecond(2);
        assertThatThrownBy(() -> second.throwThird(3, first))
                .isInstanceOf(IllegalBallThrownException.class);
    }

    @DisplayName("마지막 프레임의 세번째 투구는 첫번째 투구의 핀수가 10보다 작으면 존재할 수 없다")
    @Test
    void thirdThrowExists() {
        BallThrow first = new BallThrow(1, true);
        BallThrow second = first.throwSecond(2);
        assertThatThrownBy(() -> second.throwThird(3, first))
                .isInstanceOf(IllegalBallThrownException.class);

    }

    @DisplayName("마지막 프레임의 세번째 투구는 두번째 투구가 10개의 핀수를 가진다면 또다시 10개의 투구를 가질 수 있다")
    @Test
    void thirdThrow10Pins() {
        BallThrow first = new BallThrow(10, true);
        BallThrow second = first.throwSecond(10);
        assertThat(second.throwThird(10, first)).isEqualTo(new BallThrow(10));
    }

    @DisplayName("마지막 프레임의 세번째 투구는 첫번째와 두번째 핀수의 합이 10 이 아니라면 예외가 발생한다")
    @Test
    void thirdThrowCannotHave10PinsWhenSecondPinUnder10() {
        BallThrow first = new BallThrow(8, true);
        BallThrow second = first.throwSecond(1);
        assertThatThrownBy(() -> second.throwThird(10, first))
                .isInstanceOf(IllegalBallThrownException.class);
    }

    @DisplayName("마지막 프레임의 세번째 투구는 첫번째와 두번째 핀수의 합이 10 이라면 세번째 투구가 존재한다")
    @Test
    void thirdThrowCannotHave10PinsWhenSecondPinUnder10_() {
        BallThrow first = new BallThrow(8, true);
        BallThrow second = first.throwSecond(2);
        assertThat(second.throwThird(10, first))
                .isEqualTo(new BallThrow(10));
    }

}
