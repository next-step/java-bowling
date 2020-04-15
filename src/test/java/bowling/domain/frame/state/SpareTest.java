package bowling.domain.frame.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {
    @DisplayName("SPARE의 점수를 갖고올 수 있다.")
    @Test
    void spare() {
        String expect = "/";

        State actual = new Spare();

        assertThat(actual.toResult()).isEqualTo(expect);
    }
}