package bowling.bowlingscore;

import bowling.bowlingscore.domain.Player;
import bowling.bowlingscore.exception.CustomException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @ParameterizedTest(name = "Player 생성 실패 : name 양식 틀림 [{index}] {0}")
    @ValueSource(strings = {"asdf", "as", "ㄱㅇㅊ", "123"}) // given
    void create_fail(String name) {
        // when, then
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(CustomException.class);

    }
}