package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @DisplayName("투구 테스트")
    @Test
    void bowl_투구_테스트() {
        // given
        Frame frame = new Frame(0, 0);
        // when
        frame.bowl(3);
        int tryCount = frame.tryCount();
        // then
        assertThat(tryCount).isEqualTo(1);
    }

    @DisplayName("투구 2구 시 시도 횟수 확인 테스트")
    @Test
    void bowl_2구_테스트() {
        // given
        Frame frame = new Frame(0, 0);
        // when
        frame.bowl(3);
        frame.bowl(3);
        int tryCount = frame.tryCount();
        assertThat(tryCount).isEqualTo(2);
    }
}
