package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bowling.domain.state.BowlingPin;

public class NormalFrameTest {
    @Test
    void 생성_테스트() {
        // given
        Frame normalFrame = NormalFrame.init();
        // when & then
        assertThat(normalFrame).isInstanceOf(NormalFrame.class);
        assertThatCode(() -> NormalFrame.init()).doesNotThrowAnyException();
    }

    @Test
    void 투구_1회_테스트() {
        // given
        Frame normalFrame = NormalFrame.init();
        // when & then
        normalFrame.bowl(BowlingPin.of(10));
        assertThat(normalFrame.isDone()).isTrue();
    }

    @Test
    void 투구_2회_테스트() {
        // given
        Frame normalFrame = NormalFrame.init();
        // when & then
        normalFrame.bowl(BowlingPin.of(5));
        normalFrame.bowl(BowlingPin.of(5));
        assertThat(normalFrame.isDone()).isTrue();
    }

    @Test
    void 다음_프레임_생성_테스트() {
        // given
        Frame normalFrame = NormalFrame.init();
        // when & then
        assertThat(normalFrame.next(1)).isInstanceOf(NormalFrame.class);
        assertThatCode(() -> normalFrame.next(1)).doesNotThrowAnyException();
    }

    @Test
    void 점수_반환_테스트() {
        // given
        Frame normalFrame = NormalFrame.init();
        // when
        normalFrame.bowl(BowlingPin.of(3));
        normalFrame.bowl(BowlingPin.of(5));
        // then
        assertThat(normalFrame.frameState()).isEqualTo("3|5");
    }

    @Test
    void 점수_반환_테스트_2() {
        // given
        Frame normalFrame = NormalFrame.init();
        // when
        normalFrame.bowl(BowlingPin.of(10));
        // then
        assertThat(normalFrame.frameState()).isEqualTo("X");
    }

    @Test
    void 점수_반환_테스트_3() {
        // given
        Frame normalFrame = NormalFrame.init();
        // when
        normalFrame.bowl(BowlingPin.of(5));
        normalFrame.bowl(BowlingPin.of(5));
        // then
        assertThat(normalFrame.frameState()).isEqualTo("5|/");
    }

    @Test
    void 점수_반환_테스트_4() {
        // given
        Frame normalFrame = NormalFrame.init();
        // when
        normalFrame.bowl(BowlingPin.of(5));
        normalFrame.bowl(BowlingPin.of(0));
        // then
        assertThat(normalFrame.frameState()).isEqualTo("5|-");
    }
}
