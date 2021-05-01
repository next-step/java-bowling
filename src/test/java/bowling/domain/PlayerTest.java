package bowling.domain;

import bowling.exception.NameIncludeOtherLanguagesException;
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

    @DisplayName("Player 인스턴스에 3글자 초과의 글자 입력시 예외처리 여부 테스트")
    @Test
    void 검증_3글자() {
        // given
        String name = "pobi";

        // when and then
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(NameSizeMissMatchException.class)
                .hasMessage("( " + name + " )은 영문 3글자 또는 그 이하가 아닙니다.");
    }

    @DisplayName("Player 인스턴스가 다른 언어를 포함한 이름 입력시 예외처리 여부 테스트")
    @Test
    void 검증_다른언어() {
        // given
        String name = "1k2";

        // when and then
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(NameIncludeOtherLanguagesException.class)
                .hasMessage("( " + name + " )는 영문 글자가 아닌 다른 글자도 포함되어 있습니다.");
    }

    @DisplayName("Player 인스턴스가 소유한 이름 반환 여부 테스트")
    @Test
    void 반환() {
        // given
        String name = "KWJ";

        // when
        Player player = Player.from(name);

        // then
        assertThat(player.name()).isEqualTo(name);
    }

}