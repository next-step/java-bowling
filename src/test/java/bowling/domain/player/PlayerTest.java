package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @DisplayName("Player 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        Name name = Name.valueOf("kwj");

        // when
        Player player = Player.from(name);

        // then
        assertThat(player).isNotNull();
    }

    @DisplayName("Player 인스턴스 생성할 때 null 입력시 예외처리 테스트")
    @Test
    void 검증() {
        // given
        Name name = null;

        // when
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(NameNullPointerException.class)
                .hasMessage("Name 인스턴스가 null 입니다.");
    }

}