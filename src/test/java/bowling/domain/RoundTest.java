package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class RoundTest {

    @Test
    void 생성() {
        assertThat(Round.first()).isInstanceOf(Round.class);
    }

    @Test
    void next_횟수_초과() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Round.first()
                .next()
                .next()
                .next()
                .next()
                .next()
                .next()
                .next()
                .next()
                .next()
                .next());
    }

}