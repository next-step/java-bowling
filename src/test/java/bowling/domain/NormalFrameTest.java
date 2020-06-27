package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class NormalFrameTest {

    @Test
    @DisplayName(" 생성 테스트")
    void init() {
        assertThatCode(() -> NormalFrame.newInstance()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투구 테스트")
    void bowl() {
        assertThatCode(() -> NormalFrame.newInstance().bowl(3))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("마지막 투구인지 알려준다")
    void isLast() {
        Frame frame = NormalFrame.newInstance().bowl(3);
        assertThat(frame.isLastTrying()).isFalse();

        frame = frame.bowl(2);
        assertThat(frame.isLastTrying()).isTrue();
    }
}
