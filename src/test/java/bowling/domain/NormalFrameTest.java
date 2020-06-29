package bowling.domain;

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
    @DisplayName("투구 테스트 - 투구는 2회 까지 가능")
    void bowl() {
        Frame frame = NormalFrame.init();
        frame.bowl(3);
        assertThat(frame.isLastTryAtFrame()).isFalse();
        assertThat(frame.printFrameResult()).isEqualTo("3");

        frame.bowl(7);
        assertThat(frame.isLastTryAtFrame()).isTrue();
        assertThat(frame.printFrameResult()).isEqualTo("3|/");
    }

    @Test
    @DisplayName("현재 프레임에서 다음 프레임을 생성")
    void next() {
        Frame beforeFrame = NormalFrame.init();
        assertThatCode(() -> beforeFrame.next()).doesNotThrowAnyException();
        assertThat(!beforeFrame.equals(beforeFrame.next())).isTrue();
    }

}
