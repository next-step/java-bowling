package bowling.domain;

import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PlayerTest {

    @DisplayName("Player 객체 정상 생성")
    @Test
    public void makePlayer_정상() {
        assertThatCode(() -> {
            new Player("PJH");
        }).doesNotThrowAnyException();
    }

    @DisplayName("Player 객체 생성 실패(이름이 3글자가 아닌 경우")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"PJHA", "PJ"})
    public void makePlayer_예외(String name) {
        assertThatThrownBy(() -> {
            new Player(name);
        }).isInstanceOf(BowlingBuildingException.class)
                .hasMessageContaining(BowlingBuildingException.INVALID_PLAYER_NAME);
    }
}
