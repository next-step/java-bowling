package bowling.domain;

import bowling.exception.InvalidNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {
    @DisplayName("player 이름은 3글자 이하로 설정 가능")
    @Test
    void construct() {
        assertThat(new Name("SYS")).isNotNull();
    }

    @DisplayName("참가자 이름이 null인 경우")
    @Test
    void construct_nullName() {
        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(InvalidNameException.class)
                .hasMessageContaining("참가자 이름은 빈값이 될 수 없습니다.");
    }

    @DisplayName("3글자 초과인 player 이름")
    @Test
    void construct_longerThan3Words() {
        assertThatThrownBy(() -> new Name("longerThan3Words"))
                .isInstanceOf(InvalidNameException.class)
                .hasMessageContaining("이름의 길이는 1~3 사이의 값 입니다.");
    }
}