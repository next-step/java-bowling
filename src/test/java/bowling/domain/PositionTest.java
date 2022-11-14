package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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


    @Test
    @DisplayName("경계값기준으로 값을 초기화시키며 이동합니다")
    void shouldMoveWithBoundValue() {
        Position position = new Position(0);

        Position positionA = position.next(2);
        Position positionB = position.next(1);

        assertThat(positionA).isEqualTo(new Position(1));
        assertThat(positionB).isEqualTo(new Position(0));
    }

}
