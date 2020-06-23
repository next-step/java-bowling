package bowling.player;

import bowling.exception.BowlingBuildingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @DisplayName("Player 객체 정상 생성")
    @Test
    public void makePlayer_정상() {
        assertThatCode(() -> {
            new Player("PJH");
        }).doesNotThrowAnyException();
    }

    @DisplayName("Player 객체 생성 실패 (이름이 3글자가 아니거나 null)")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"PJHA", "P"})
    public void makePlayer_실패(String name) {
        assertThatThrownBy(() -> {
            new Player(name);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_PLAYER_NAME);
    }
}
