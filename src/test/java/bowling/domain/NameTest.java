package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @DisplayName("플레이어 이름은 3글자에 영문자여야 한다.")
    @Test
    void create() {
        assertThat(new Name("PJS")).isEqualTo(new Name("PJS"));
    }

    @DisplayName("플레이어 이름이 한글이나 4글자 이상이 오면 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ABCD", "김코딩"})
    void create_error(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 3글자의 영문자여야 합니다.");
    }

}