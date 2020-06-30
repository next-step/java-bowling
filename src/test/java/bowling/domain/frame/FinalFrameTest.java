package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FinalFrameTest {
    @Test
    @DisplayName(" 생성 테스트")
    void init() {
        assertThatCode(() -> FinalFrame.init()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투구 테스트")
    void bowl() {
        Frame frame = FinalFrame.init();

        frame.bowl(3);
        frame.bowl(7);
        assertThat(frame.isLastTryAtFrame()).isFalse();
        assertThat(frame.printFrameResult()).isEqualTo("3|/");

        frame.bowl(7);
        assertThat(frame.isLastTryAtFrame()).isTrue();
        assertThat(frame.printFrameResult()).isEqualTo("3|/|7");

    }

    @Test
    @DisplayName("마지막 프레임에서 다음 프레임 생성시 오류")
    void next() {
        Frame finalFrame = FinalFrame.init();
        assertThatIllegalStateException()
                .isThrownBy(() -> finalFrame.next());
    }

}
