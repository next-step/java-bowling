package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Pin: 각 투구마다 넘긴 핀 수 테스트")
class PinTest {

    @DisplayName("넘김 핀 수를 입력받아 생성")
    @Test
    public void getCount_ReturnCount() {
        int count = 5;
        Pin pin = new Pin(count);
        assertThat(pin.getCount()).isEqualTo(count);
    }

    @DisplayName("넘긴 핀 수는 10개를 넘을 수 없다")
    @Test
    public void create_WithCountMoreThan10_ExceptionThrown() {
        assertThatThrownBy(() -> {
            new Pin(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
