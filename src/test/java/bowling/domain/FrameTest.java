package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class FrameTest {

    @Test
    @DisplayName(" 생성 테스트")
    void init() {
        assertThatCode(() -> Frame.newInstance()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("투구 테스트")
    void bowl() {
        assertThatCode(() -> Frame.newInstance().bowl(3))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("마지막 투구인지 알려준다")
    void isLast() {
        Frame frame = Frame.newInstance().bowl(3);
        assertThat(frame.isLast(2)).isFalse();

        frame = frame.bowl(3);
        assertThat(frame.isLast(2)).isTrue();
    }
}
