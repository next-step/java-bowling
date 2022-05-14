package bowling.domain.game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @ParameterizedTest(name = "참가자 이름은 길이3의 영어 문자")
    @ValueSource(strings = {"", "  ", "김철수", "abcd"})
    void validation(String invalidName) {
        assertThatThrownBy(() -> new Player(invalidName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

