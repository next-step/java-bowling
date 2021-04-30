package bowling.domain;

import bowling.exception.NameSizeMissMatchException;
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
        String name = "kwj";

        // when
        Player player = Player.from(name);

        // then
        assertThat(player).isNotNull();
    }

    @DisplayName("Player 인스턴스에 3글자 초과의 글자 입력 여부 테스트")
    @Test
    void 검증_3글자() {
        // given
        String name = "pobi";

        // when and then
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(NameSizeMissMatchException.class)
                .hasMessage("( pobi )은 영문 3글자 또는 그 이하가 아닙니다.");
    }

}