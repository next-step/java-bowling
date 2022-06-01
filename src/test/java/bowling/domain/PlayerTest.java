package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @DisplayName("player 이름은 3글자 이하로 설정 가능")
    @Test
    void construct() {
        assertThat(new Player("SYS")).isNotNull();
    }

    @DisplayName("player 이름이 null인 경우")
    @Test
    void construct_nullName() {
        assertThatThrownBy(() -> new Player(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자 이름음 null이 될 수 없습니다.");
    }

    @DisplayName("3글자 초과인 player 이름")
    @Test
    void construct_longerThan3Words() {
        assertThatThrownBy(() -> new Player("longerThan3Words"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("사이의 숫자 입니다.");
    }
}