package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @DisplayName("플레이어 이름은 3글자에 영문자여야 한다.")
    @Test
    void create() {
        assertThat(new Name("PJS")).isEqualTo(new Name("PJS"));
    }

    @DisplayName("플레이어 이름은 3글자에 영문자여야 한다.")
    @Test
    void create_error() {
        assertThatThrownBy(() -> new Name("ABCD"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Name("김코딩"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}