package bowling.domain;

import bowling.domain.state.Gutter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {

    @Test
    @DisplayName("Gutter인지 확인")
    void isGutter() {
        Pitch pitch = Pitch.from(0);

        Gutter gutter = Gutter.from(pitch);

        assertThat(gutter.isGutter()).isTrue();
    }

    @Test
    @DisplayName("Gutter가 아닌지 확인")
    void isNotGutter() {
        Pitch pitch = Pitch.from(1);

        Gutter gutter = Gutter.from(pitch);

        assertThat(gutter.isGutter()).isFalse();
    }

}