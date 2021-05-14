package bowling;

import bowling.entity.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.entity.Pin.RANGE_OVER_PIN_MESSAGE;
import static bowling.entity.Pin.SUM_RANGE_OVER_PIN_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PinTest {
    @Test
    @DisplayName("핀 생성")
    public void createPin() {
        int fallenPin = 5;

        Pin pin = new Pin(fallenPin);

        assertThat(pin.equals(new Pin(fallenPin))).isTrue();
    }

    @Test
    @DisplayName("쓰러트릴 수 있는 최대 핀을 벗어나는 핀 생성")
    public void maxOverFallenPin() {
        int fallenPin = 11;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Pin(fallenPin))
                .withMessageMatching(RANGE_OVER_PIN_MESSAGE);

    }

    @Test
    @DisplayName("쓰러트릴 수 있는 최소 핀을 벗어나는 핀 생성")
    public void minUnderFallenPin() {
        int fallenPin = -1;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Pin(fallenPin))
                .withMessageMatching(RANGE_OVER_PIN_MESSAGE);

    }

    @Test
    @DisplayName("핀 생성 실패")
    public void createPinFail() {
        Pin fallenPin = new Pin(6);
        Pin secondFallenPin = new Pin(6);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> fallenPin.sum(secondFallenPin))
                .withMessageMatching(SUM_RANGE_OVER_PIN_MESSAGE);
    }
}
