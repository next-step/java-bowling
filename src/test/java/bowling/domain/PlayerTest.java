package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("플레이어 생성 - 성공")
    void createPlayer_success() {
        new Player("chy");
    }

    @Test
    @DisplayName("플레이어 생성 - 이름 사이즈 에러")
    void createPlayer_nameSizeMismatch() {
        assertThatThrownBy(() -> {
            new Player("chye");
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Name size must be 3");
    }
}
