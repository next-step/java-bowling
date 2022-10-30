package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @ParameterizedTest
    @CsvSource({"1,2", "2,3"})
    void shouldMove(int startPosition, int nextPosition) {
        Position position = new Position(startPosition);

        assertThat(position.next()).isEqualTo(new Position(nextPosition));
    }

}
