package bowling.domain;

import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @Test
    void of() {
        assertThat(Player.of("SKT").frames().getFrames())
                .containsExactly(NormalFrame.init());
    }

    @Test
    void ofException() {
        assertThatThrownBy(() -> Player.of("tester"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
