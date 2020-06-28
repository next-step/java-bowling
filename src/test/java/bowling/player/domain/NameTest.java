package bowling.player.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    @DisplayName("이름은 null 이거나 빈문자열일 수 없다.")
    void not_null_or_empty(String name) {
        assertThatThrownBy(() -> Name.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name 객체를 생성할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "def"})
    @DisplayName("이름은 세글자의 영어 단어로 생성할 수 있다.")
    void create_name(String name) {
        assertThat(Name.of(name).getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcd", "defgqa"})
    @DisplayName("이름은 세글자의 영어가 아닌 경우 생성할 수 없다.")
    void dont_create_name(String name) {
        assertThatThrownBy(() -> Name.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name 객체를 생성할 수 없습니다.");
    }
}