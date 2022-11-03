package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionsTest {

    @Test
    void shouldReturnNextPosition() {
        Positions positions = new Positions(0);
        Position next = positions.next();

        assertThat(next.equals(new Position(1))).isTrue();
    }

}
