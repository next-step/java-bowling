package bowling.domain.frame.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {
    @DisplayName("GUTTER 점수를 갖고올 수 있다.")
    @Test
    void gutter() {
        State gutter = new Gutter();

        assertThat(gutter.toString()).isEqualTo("-");
    }
}