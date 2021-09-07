package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {
    @Test
    void create() {
        Name name = new Name("ABC");
        assertThat(name).isEqualTo(new Name("ABC"));
    }

    @DisplayName("3자리를 초과하여 이름 입력시 에러 발생")
    @Test
    void error() {
        assertThatThrownBy(() -> new Name("ABCD")).isInstanceOf(IllegalArgumentException.class);
    }
}