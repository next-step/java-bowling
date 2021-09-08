package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinTest {

    @DisplayName("쓰러트린 공의 갯수는 0개에서 10개다.")
    @Test
    void validate() {
        assertThat(new Pin(1)).isEqualTo(new Pin(1));
        assertThatThrownBy(() -> new Pin(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

}