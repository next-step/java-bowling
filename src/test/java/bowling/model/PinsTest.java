package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Pins 테스트")
public class PinsTest {

    @DisplayName("생성 테스트")
    @Test
    public void generatePinsTest() {
        assertThatCode(Pins::new);
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

        assertThat(pins.drop(5)).isEqualTo(5);
    }

    @DisplayName("핀 쓰러트리기 에러 테스트 ")
    @Test
    public void dropsPinsAbnormalTest() {
        Pins pins = new Pins();
        pins.drop(10);

        assertThatIllegalStateException()
            .isThrownBy(() -> pins.drop(5))
            .withMessageContaining("쓰러진 핀의 갯수가 더 많을 수 없습니다.");
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