package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @DisplayName("플레이어를 생성한다")
    @Test
    void initTest() {
        String name = "bao";
        Player player = Player.from(name);
        assertThat(player.name()).isEqualTo(name);
    }

    @DisplayName("플레이어의 이름은 3글자를 초과할 수 없다")
    @Test
    void initLengthExceptionTest() {
        assertThatThrownBy(() -> Player.from("pandahun"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어의 이름이 영어가 아니면 예외를 발생시킨다")
    @Test
    void initLanguageExceptionTest() {
        assertThatThrownBy(() -> {
            Player.from("1");
            Player.from("다훈");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}