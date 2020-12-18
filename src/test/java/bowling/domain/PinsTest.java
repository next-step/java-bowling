package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created : 2020-12-18 오전 8:47
 * Developer : Seo
 */
class PinsTest {
    @DisplayName("생성")
    @Test
    void constructor() {
        assertThat(new Pins(10)).isNotNull().isInstanceOf(Pins.class);
    }

    @DisplayName("핀 유효성 체크")
    @Test
    void invalid() {
        assertThatThrownBy(() -> new Pins(11))
                .withFailMessage("볼링 핀은 최대 10개입니다. ")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Pins(-1))
                .withFailMessage("볼링 핀은 최소 0개입니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("갯수 체크")
    @Test
    void get() {
        assertThat(new Pins(10).get()).isEqualTo(10);
    }
}
