package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static bowling.exception.BowlingExceptionCode.INVALID_PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @ParameterizedTest
    @NullAndEmptySource
    void nameIsNullOrEmptyGiven_ThrowExp(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(INVALID_PLAYER_NAME.getMessage());
    }
}
