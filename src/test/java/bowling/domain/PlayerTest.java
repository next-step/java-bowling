package bowling.domain;

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
        String name = "KWJ";

        // when
        Player player = Player.from(name);

        // then
        assertThat(player).isNotNull();
    }

    @DisplayName("Player 인스턴스에 입력된 이름이 3글자 초과 또는 빈 문자인지 확인하는 테스트")
    @Test
    void 검증_() {
        // given
        String name = "POBI";

        // when
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(NameSizeMissMatchException.class)
                .hasMessage("(+"+name+"+)은 영문 3글자 또는 그 이하가 아닙니다.");
    }


}