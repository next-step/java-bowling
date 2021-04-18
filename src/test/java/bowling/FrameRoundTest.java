package bowling;

import bowling.domain.frame.FrameRound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FrameRoundTest {

    @Test
    @DisplayName("프레임 각 라운드가 1 미만 10 초과를 할 수 없다.")
    void validTest() {
        assertThatThrownBy(() -> new FrameRound(11))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new FrameRound(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("프레임 시작 라운드 테스트")
    void startTest() {
        assertThat(FrameRound.start()).isEqualTo(new FrameRound(1));
    }

    @Test
    @DisplayName("프레임 마지막 라운드 테스트")
    void lastTest() {
        assertThat(FrameRound.last()).isEqualTo(new FrameRound(10));
    }

    @Test
    @DisplayName("다음 라운드 테스트")
    void nextTest() {
        assertThat(FrameRound.start().next()).isEqualTo(new FrameRound(2));
    }
}
