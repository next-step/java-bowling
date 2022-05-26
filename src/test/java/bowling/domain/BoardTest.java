package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    void init() {
        assertThat(Board.init().frames().size()).isEqualTo(10);
    }
}
