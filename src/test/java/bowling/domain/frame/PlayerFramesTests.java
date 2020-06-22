package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerFramesTests {
    @DisplayName("아무 것도 없는 상태로 초기화")
    @Test
    void create() {
        PlayerFrames playerFrames = PlayerFrames.createEmpty();

        assertThat(playerFrames.size()).isEqualTo(0);
    }
}
