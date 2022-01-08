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
        Frame next = first.bowl(new Pins(1)).bowl(new Pins(3));

        // then
        assertThat(next.getIndex()).isEqualTo(2);
    }

    @Test
    void 스트라이크_다음_프레임_생성() {
        // given
        Frame first = NormalFrame.first();

        // when
        Frame next = first.bowl(new Pins(Pins.MAX_RANGE));

        // then
        assertThat(next.getIndex()).isEqualTo(2);
    }

    @DisplayName("한 번 투구 후 현재 그대로 현재 프레임인지 확인")
    @Test
    void 한_번_투구_완료_현재_프레임_확인() {
        // given
        Frame first = NormalFrame.first();

        // when가
        Frame next = first.bowl(new Pins(3));

        // then
        assertThat(next).isEqualTo(first);
    }

    @DisplayName("해당 프레임 투구 완료 후 마지막 전 프레임이면 마지막 프레임 생성")
    @Test
    void 마지막_프레임_생성() {
        // given
        Frame first = NormalFrame.first();
        // when
        Frame next = first.bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10))
                .bowl(new Pins(10)); // 10프레임

        // then
        assertThat(next).isInstanceOf(LastFrame.class);
    }

//    @Test
//    void 미스_점수_계산() {
//        // given
//        Frame missFrame = NormalFrame.first();
//        missFrame.bowl(new Pins(1));
//        missFrame.bowl(new Pins(3));
//
//        // when
//        int score = missFrame.calculateScore();
//
//        // then
//        assertThat(score).isEqualTo(4);
//    }
//
//    @Test
//    void 스페어_점수_계산() {
//        // given
////        Frame spareFrame = NormalFrame.first();
////        spareFrame.bowl(new Pins(1));
////        Frame nextFirstFrame = spareFrame.bowl(new Pins(9));
////
////        nextFirstFrame.bowl(new Pins(3));
////
////        // when
////        int score = spareFrame.calculateScore();
////
////        // then
////        assertThat(score).isEqualTo(13);
//    }
}