package bowling.player.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @DisplayName("게임 참여자를 생성할 수 있다")
    @Test
    public void player() {
        Player player = Player.of("LJW");
        Player player2 = Player.of("LJW");

        assertThat(player).isEqualTo(player2);
    }

    @DisplayName("게임 참여자의 이름은 영문 3자만 가능하다")
    @ParameterizedTest
    @ValueSource(strings = {"하하하", "ab", "a", ""})
    public void invalidNameTest(String name) {
        assertThatThrownBy(() -> Player.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 3글자로 입력해야 합니다.");
    }

    @DisplayName("게임 참여자의 이름은 영문 3자만 가능하다")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "qwe", "LJW"})
    public void validNameTest(String name) {
        Player player = Player.of(name);
        assertThat(player.getName()).isEqualTo(name);
    }

}
