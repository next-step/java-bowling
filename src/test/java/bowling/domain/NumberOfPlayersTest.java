package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class NumberOfPlayersTest {

    @Test
    @DisplayName("참가자의 수가 0 또는 그 이하의 값이면 예외 처리한다.")
    void throwExceptionIfNumberOfPlayersIsUnderZero() {
        assertAll(
            () -> assertThatThrownBy(() -> new NumberOfPlayers(0)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> new NumberOfPlayers(-1)).isInstanceOf(IllegalArgumentException.class)
        );
    }

}
