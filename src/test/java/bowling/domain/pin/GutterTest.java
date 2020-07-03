package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {
    @Test
    @DisplayName("Gutter 의 경우 핀 값을 전달받지 않는다.")
    void create_gutter() {
        assertThat(Gutter.of()).isNotNull();
    }
}