package bowling_step3.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    void name은3이하여야한다() {
        Player correctPlayer = new Player("abc");
        assertThat(correctPlayer).isInstanceOf(Player.class);
        assertThatThrownBy(()->new Player("abcd")).isInstanceOf(IllegalArgumentException.class);
    }
}
