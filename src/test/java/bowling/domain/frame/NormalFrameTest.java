package bowling.domain.frame;

import bowling.domain.status.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class NormalFrameTest {
    @Test
    @DisplayName(" 생성 테스트")
    void init() {
        assertThatCode(() -> NormalFrame.init()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투구는 2회까지 가능하다.")
    void bowl() {
        Frame frame = NormalFrame.init();

        Status status = frame.bowl(3);
        assertThat(status.canPlayMore()).isTrue();

        status = frame.bowl(4);
        assertThat(status.canPlayMore()).isFalse();

    }

    @Test
    @DisplayName("현재 프레임에서 다음 프레임을 생성")
    void next() {
        Frame beforeFrame = NormalFrame.init();
        assertThatCode(() -> beforeFrame.nextFrame(0)).doesNotThrowAnyException();
        assertThat(!beforeFrame.equals(beforeFrame.nextFrame(0))).isTrue();
    }

}
