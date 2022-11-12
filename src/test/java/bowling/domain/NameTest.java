package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @Test
    @DisplayName("이름 생성")
    void create_name() {
        Name name = new Name("TDD");
        assertThat(name).isEqualTo(new Name("TDD"));
    }

    @DisplayName("이름 3글자 아닐 경우 에러 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "TEST", "TT"})
    void name_length(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 3글자여야 합니다.");
    }

}
