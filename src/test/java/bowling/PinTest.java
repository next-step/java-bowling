package bowling;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PinTest {

    @Test
    @DisplayName("객체 비교")
    void compareWithPinObjectTest() {
        Pin pin = new Pin(5);
        Pin pin1 = new Pin(5);

        assertThat(pin).isEqualTo(pin1);
    }

    @Test
    @DisplayName("적중한 볼링 핀 수 예외 처리")
    void validatePinByHitOfCount() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Pin pin = new Pin(11);
        });

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Pin pin = new Pin(-1);
        });
    }
}
