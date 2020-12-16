package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TryTest {

    @Test
    void plusTest() {
        assertThat(Try.of(5).plus(Try.of(3))).isEqualTo(Try.of(8));
    }
}
