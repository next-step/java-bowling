package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Pins 테스트")
public class PinTests {

    @DisplayName("생성 테스트")
    @Test
    public void generatePinsTest() {
        assertThatCode(Pin::new);
    }

    @DisplayName("초기값 테스트")
    @Test
    public void initPinsTest() {
        Pin pin = new Pin();

        assertTrue(pin.isRemain(10));
    }

    @DisplayName("핀 쓰러트리기 테스트")
    @Test
    public void dropPinsTest() {
        Pin pin = new Pin();

        assertThat(pin.drop(5)).isEqualTo(5);
    }

    @DisplayName("핀 쓰러트리기 에러 테스트 ")
    @Test
    public void dropsPinsAbnormalTest() {
        Pin pin = new Pin();
        pin.drop(10);

        assertThatIllegalStateException()
                .isThrownBy(() -> pin.drop(5))
                .withMessageContaining("Remain pin count must be greater than dropping pin count.");
    }

    @DisplayName("핀 리셋 테스트 ")
    @Test
    public void resetPinsTest() {
        Pin pin = new Pin();
        pin.drop(10);
        pin.reset();

        assertTrue(pin.isRemain(10));
    }
}
