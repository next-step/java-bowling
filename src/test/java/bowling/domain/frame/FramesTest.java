package bowling.domain.frame;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void 첫_프레임_생성() {
        // given, when
        Frames frames = Frames.first();

        // then
        assertThat(frames.lastFrameIndex()).isEqualTo(FrameIndex.MIN_INDEX);
    }

    @DisplayName("프레임이 진행 상태일 경우 다음 프레임을 생성하지 않는다. - 스트라이크가 아닌 첫 투구")
    @Test
    void 프레임이_진행_상태일_때_다음_프레임을_생성하지_않는다() {
        // given
        Frames frames = Frames.first();

        // when
        frames.bowl(Pins.create(5));

        // then
        assertThat(frames.lastFrameIndex()).isEqualTo(FrameIndex.MIN_INDEX);
    }

    @Test
    void 프레임이_종료_상태로_변경되면_다음_프레임을_생성한다_스페어() {
        // given
        Frames frames = Frames.first();

        // when
        frames.bowl(Pins.create(3));
        frames.bowl(Pins.create(7));

        // then
        assertThat(frames.lastFrameIndex()).isEqualTo(2);
    }

    @Test
    void 프레임이_종료_상태로_변경되면_다음_프레임을_생성한다_스트라이크() {
        // given
        Frames frames = Frames.first();

        // when
        frames.bowl(Pins.create(10));

        // then
        assertThat(frames.lastFrameIndex()).isEqualTo(2);
    }
}