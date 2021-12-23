package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MissTest {

    @Test
    @DisplayName("핀을 한개도 못 맞추면 거터, 맞추면 맞춘 갯수 표시 테스트")
    void markTest() {
        assertThat(new Miss(new Pins(5), new Pins(4)).getMark()).isEqualTo("4");
        assertThat(new Miss(new Pins(5), new Pins(0)).getMark()).isEqualTo("-");
    }
}
