package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {
    @ParameterizedTest(name = "플레이어 이름을 받아 Player 객체를 생성한다; 이름: {0}")
    @ValueSource(strings = {"a", "ab", "abc"})
    void create(String name) {
        new Player(name);
    }

    @Test
    @DisplayName("이름이 비어있을 경우 예외를 던진다.")
    void createWithEmptyName() {
        assertThatThrownBy(() -> new Player(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("이름은 공백일수 없습니다");
    }

    @ParameterizedTest(name = "3자가 넘어가는 이름일 경우 예외를 던진다; 이름: {0}")
    @ValueSource(strings = {"abcd", "abcde", "aaaaaaaaa"})
    void createWithNameLongerThanThreeLength(String name) {
        assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("이름의 길이는 3자를 초과할 수 없습니다");
    }

    @ParameterizedTest(name = "이름에 알파벳이 아닌 문자가 포함될 경우 예외를 던진다; 이름: {0}")
    @ValueSource(strings = {"123", "!@#", "가나다"})
    void createWithNameContainsNonAlphabet(String name) {
        assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("이름에는 알파벳만 포함되어야 합니다");
    }
}
