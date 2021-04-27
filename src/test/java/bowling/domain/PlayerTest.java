package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @DisplayName("Player 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        String name = "kwj";

        // when
        Player player = Player.from(name);

        // then
        assertThat(player).isNotNull();
    }

    @DisplayName("Player 인스턴스 생성 여부 테스트")
    @Test
    void 검증_() {
        // given
        String name = "KWJ";

        // when
        Player player = Player.from(name);

        // then
        assertThat(player).isNotNull();
    }
}