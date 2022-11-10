package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RoundTest {

    @Test
    void create() {
        Round actual = new Round(10);

        assertThat(actual).isEqualTo(new Round(10));
    }
}
