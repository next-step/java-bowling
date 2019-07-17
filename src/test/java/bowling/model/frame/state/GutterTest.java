package bowling.model.frame.state;

import bowling.model.DownPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.DownPin.MIN;
import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {

    @DisplayName("싱글톤 확인")
    @Test
    void check_singleton() {
        assertThat(Gutter.getInstance()).isEqualTo(Gutter.getInstance());
    }

    @DisplayName("핀의 개수가 " + MIN + "인 경우 거터이다")
    @Test
    void isMatch_pinsZero_thanTrue() {
        // when
        boolean result = Gutter.isMatch(DownPin.DOWN_ZERO);

        // then
        assertThat(result).isTrue();
    }
}