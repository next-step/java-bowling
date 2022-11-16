package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {
    @DisplayName("사용자 이름이 영어로 되어 있고 알파벳 갯수가 3개 이하이면 사용자 객체를 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = { "a", "z", "aZc" })
    void create(String name) {
        assertThat(new User(name)).isNotNull();
    }

    @DisplayName("사용자 이름이 null 또는 공백이거나 영어가 아니거나 알파벳 갯수가 4개 이상이면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { "abcd", "abcdef", "한글" })
    void create_when_invalid_name(String name) {
        assertThatThrownBy(() -> new User(name)).isInstanceOf(IllegalArgumentException.class).hasMessage("User name must be no more than 3 characters in English.");
    }
}
