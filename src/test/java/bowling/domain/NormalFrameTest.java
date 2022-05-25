package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {
    @Test
    @DisplayName("1~9에 해당하는 프레임 객체 생성을 확인한다")
    void checkedFrameObjectGenerate() {
        // given
        NormalFrame normalFrame = NormalFrame.initialize();

        // when & then
        assertAll(
                () -> assertThat(normalFrame.getFrameNumber()).isEqualTo(FrameNumber.MIN_FRAME_NUMBER),
                () -> assertThat(normalFrame.isEnd()).isFalse(),
                () -> assertThat(normalFrame.symbol()).isBlank()
        );
    }

    @Test
    @DisplayName("스트라이크가 아닌 경우, 현재 프레임인지 확인한다")
    void checkedCurrentFrame() {
        // given
        NormalFrame normalFrame = NormalFrame.initialize();

        // when
        Frame frame = normalFrame.bowl(new Pitching(5));

        // then
        assertThat(frame).isEqualTo(normalFrame);
    }

    @Test
    @DisplayName("다음 프레임으로 넘어갔는지 확인한다")
    void checkedNextFrame() {
        // given
        NormalFrame normalFrame = NormalFrame.initialize();

        // when
        Frame frame = normalFrame.bowl(new Pitching(10));

        // then
        assertThat(frame.getFrameNumber()).isEqualTo(2);
    }
}
