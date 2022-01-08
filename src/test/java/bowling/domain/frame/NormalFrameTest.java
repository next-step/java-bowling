package bowling.domain.frame;

import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    void 첫_프레임_생성() {
        // given
        Frame first = NormalFrame.first();

        // when, then
        assertThat(first.getIndex()).isEqualTo(FrameIndex.MIN_INDEX);
        assertThat(first.isEnd()).isFalse();
        assertThat(first.symbol()).isBlank();
    }

    @Test
    void 두_번_투구_완료_다음_프레임_생성() {
        // given
        Frame first = NormalFrame.first();

        // when
        Frame next = first.bowl(Pins.create(1)).bowl(Pins.create(3));

        // then
        assertThat(next.getIndex()).isEqualTo(2);
    }

    @Test
    void 스트라이크_다음_프레임_생성() {
        // given
        Frame first = NormalFrame.first();

        // when
        Frame next = first.bowl(Pins.create(Pins.MAX_RANGE));

        // then
        assertThat(next.getIndex()).isEqualTo(2);
    }

    @DisplayName("한 번 투구 후 현재 그대로 현재 프레임인지 확인")
    @Test
    void 한_번_투구_완료_현재_프레임_확인() {
        // given
        Frame first = NormalFrame.first();

        // when가
        Frame next = first.bowl(Pins.create(3));

        // then
        assertThat(next).isEqualTo(first);
    }

    @DisplayName("해당 프레임 투구 완료 후 마지막 전 프레임이면 마지막 프레임 생성")
    @Test
    void 마지막_프레임_생성() {
        // given
        Frame first = NormalFrame.first();
        // when
        Frame next = first.bowl(Pins.create(10))
                .bowl(Pins.create(10))
                .bowl(Pins.create(10))
                .bowl(Pins.create(10))
                .bowl(Pins.create(10))
                .bowl(Pins.create(10))
                .bowl(Pins.create(10))
                .bowl(Pins.create(10))
                .bowl(Pins.create(10)); // 10프레임

        // then
        assertThat(next).isInstanceOf(LastFrame.class);
    }
}