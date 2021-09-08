package bowling.domain;

import bowling.exception.InvalidInputException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @DisplayName("플레이어 생성 성공 테스트")
    @Test
    public void create_player() {
        Player player = new Player("JHJ");
        assertThat(player).isEqualTo(new Player("JHJ"));
    }

    @DisplayName("플레이어 이름 글자수 올바르지 않음 생성 실패 테스트")
    @Test
    public void create_player_error() {
        assertThatThrownBy(() -> new Player("J"))
                .isInstanceOf(InvalidInputException.class);
    }
}
