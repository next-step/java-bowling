package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PinTest {

    @Test
    @DisplayName("잘못된 핀개수 초과 에러")
    public void PinTenOverException() {
        assertThatThrownBy(() -> Pin.of(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("잘못된 핀개수 초과 에러")
    public void PinZeroUnderException() {
        assertThatThrownBy(() -> Pin.of(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
