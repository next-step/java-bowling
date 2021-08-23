package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.exception.LimitException;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void 생성() {
        Player player = Player.of("asd");
        assertThat(player).isEqualTo(Player.of("asd"));
    }

    @Test
    void 세글자_예외() {
        assertThatThrownBy(() -> {
            Player.of("asdf");
        }).isInstanceOf(LimitException.class);
    }


}