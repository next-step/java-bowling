package bowling;

import bowling.domain.frame.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinTest {

    @Test
    @DisplayName("핀의 개수가 음수거나, 10개를 초과하는경우 예외발생 테스트")
    void valid() {
        assertThatThrownBy(() -> new Pin(-1))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Pin(11))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
