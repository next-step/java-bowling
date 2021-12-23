package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    @DisplayName("프레임 생성 테스트")
    void create() {
        Frame first = new Frame(1);
        assertThat(first).isEqualTo(new Frame(1));
    }

    @Test
    @DisplayName("프레임 증가")
    void upFrame() {
        Frame frame = new Frame(1);
        assertThat(frame.nextFrame()).isEqualTo(new Frame(2));
    }

}