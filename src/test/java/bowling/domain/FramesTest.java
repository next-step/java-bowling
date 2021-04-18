package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("프레임 일급 컬렉션 투구 테스트")
    @Test
    void create_프레임_투구테스트() {
        // given
        Frames frames = new Frames(new Frame(0, 0));
        // when
        frames.bowl(5);
        // then
        assertThat(frames.currentFrame()).isEqualTo(new Frame(5, 1));
    }
}
