package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("Pins 테스트")
public class PinsTests {
    /*
     * Pins
     * 볼링 프레임의 남은 볼링 핀 수를 관리한다.
     * 초기값은 10이며, 최소값은 0이다.
     * 쓰러트린 핀 수를 입력받으면 남은 볼링 핀수를 갱신하고 Score 값을 반환한다.
     * 쓰러트린 핀 수가 남은 볼링 핀수보다 많으면 에러를 발생한다.
     * 남은 볼링 핀 수를 리셋할 수 있다. (다시 10개로)
     */

    @DisplayName("생성 테스트")
    @Test
    public void generatePinsTest() {
        assertThatCode(() -> new Pins());
    }

    @DisplayName("초기값 테스트")
    @Test
    public void initPinsTest() {
        Pins pins = new Pins();

        assertTrue(pins.isRemain(10));
    }

    @DisplayName("핀 쓰러트리기 테스트")
    @Test
    public void dropPinsTest() {
        Pins pins = new Pins();

        assertThatCode(() -> pins.drop(5));
    }

    @DisplayName("핀 쓰러트리기 에러 테스트 ")
    @Test
    public void dropsPinsAbnormalTest() {
        Pins pins = new Pins();
        pins.drop(10);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> pins.drop(5))
                .withMessageContaining("Remain pin count must be greater than dropping pin count.");
    }

    @DisplayName("핀 리셋 테스트 ")
    @Test
    public void resetPinsTest() {
        Pins pins = new Pins();
        pins.drop(10);
        pins.reset();

        assertTrue(pins.isRemain(10));
    }
}
