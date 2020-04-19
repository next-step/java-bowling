package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Pins 테스트")
public class PinTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> Pin.of(6));
        assertThatCode(Pin::ofMax);
    }

    @DisplayName("생성 에러 테스트")
    @Test
    public void generateErrorTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Pin.of(-5));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Pin.of(16));
    }


    @DisplayName("핀 합계 테스트")
    @Test
    public void addTest() {
        Pin pin = Pin.of(7);
        assertThat(pin.add(Pin.of(3))).isEqualTo(Pin.of(10));
    }

    @DisplayName("핀 비교 테스트 ")
    @Test
    public void equalTest() {
        Pin pin = Pin.of(7);
        assertTrue(pin.isEqualTo(7));
        assertFalse(pin.isEqualTo(3));
    }

    @DisplayName("핀 스코어 전환 테스트 ")
    @Test
    public void toScoreTest() {
        Pin pin = Pin.of(7);
        assertThat(pin.toScore()).isEqualTo(Score.of(7));
    }
}
