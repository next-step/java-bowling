package bowling.domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void description() {
        assertThat(new Strike().description()).isEqualTo("X");
    }

    @Test
    void tries() {
        assertThat(new Strike().tries()).isEqualTo(1);
    }
}
