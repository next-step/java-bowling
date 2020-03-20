package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PinTest {

    @Test
    @DisplayName("핀의 객체 값 비교")
    void checkObjectValueTest() {
        Pin pin = new Pin(5);
        assertThat(pin).isEqualTo(new Pin(5));
    }

    @Test
    @DisplayName("생성된 핀의 값 가져오기")
    void getScoreTest() {
        Pin pin = new Pin(6);
        assertThat(pin.getCountOfHit()).isEqualTo(6);
    }

    @Test
    @DisplayName("핀의 유효성 체크")
    void validatePinValueTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Pin(11);
        });

        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Pin(-1);
        });
    }
}
