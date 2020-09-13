package bowling.state.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateTest {

    @Test
    @DisplayName("스트라이크 기호 확인")
    void state() {
        String mark = State.STRIKE.getMark();
        assertThat(mark).isEqualTo("X");
    }

    @Test
    @DisplayName("스페어 기호 확인")
    void spare() {
        String mark = State.SPARE.getMark();
        assertThat(mark).isEqualTo("/");
    }

    @Test
    @DisplayName("거터 기호 확인")
    void gutter() {
        String mark = State.GUTTER.getMark();
        assertThat(mark).isEqualTo("-");
    }

}
