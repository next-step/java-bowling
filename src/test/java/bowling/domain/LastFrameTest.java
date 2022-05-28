package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LastFrameTest {
    @Test
    @DisplayName("마지막 프레임 객체 생성을 확인한다")
    void checkedLastFrameObjectGenerate() {
        // given
        LastFrame lastFrame = new LastFrame();

        // when & then
        assertAll(
                () -> assertThat(lastFrame.getFrameNumber()).isEqualTo(FrameNumber.MAX_FRAME_NUMBER),
                () -> assertThat(lastFrame.isEnd()).isFalse(),
                () -> assertThat(lastFrame.symbol()).isBlank()
        );
    }
}
