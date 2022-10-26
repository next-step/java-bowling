package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    void shouldMove() {
        Position position = new Position(1);

        assertThat(position.next()).isEqualTo(new Position(2));
    }

}
