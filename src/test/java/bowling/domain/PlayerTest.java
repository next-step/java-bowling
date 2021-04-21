package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("이름을 3글자 초과로 입력하면 예외가 발생한다")
    void maxNameLength() {
        assertThatThrownBy(() -> {
            Player.from("shkwon");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}