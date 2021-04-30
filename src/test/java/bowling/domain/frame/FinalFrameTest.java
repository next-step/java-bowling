package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bowling.domain.state.BowlingPin;

public class FinalFrameTest {

    @Test
    void 생성_테스트() {
        // given
        Frame finalFrame = FinalFrame.init();
        // when & then
        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
        assertThatCode(() -> FinalFrame.init()).doesNotThrowAnyException();
    }

    @Test
    void 투구_2회_테스트() {
        // given
        Frame frame = FinalFrame.init();
        Frame frame2 = FinalFrame.init();
        // when
        frame.bowl(BowlingPin.of(3));
        frame.bowl(BowlingPin.of(5));

        frame2.bowl(BowlingPin.of(3));
        // then
        assertThat(frame.isDone()).isTrue();
        assertThat(frame2.isDone()).isFalse();
    }

    @Test
    void 보너스_투구_스트라이크_테스트() {
        // given
        Frame frame = FinalFrame.init();
        Frame frame2 = FinalFrame.init();
        Frame frame3 = FinalFrame.init();
        // when
        frame.bowl(BowlingPin.of(10));

        frame2.bowl(BowlingPin.of(10));
        frame2.bowl(BowlingPin.of(7));

        frame3.bowl(BowlingPin.of(10));
        frame3.bowl(BowlingPin.of(7));
        frame3.bowl(BowlingPin.of(2));
        // then
        assertThat(frame.isDone()).isFalse();
        assertThat(frame2.isDone()).isFalse();
        assertThat(frame3.isDone()).isTrue();
    }

    @Test
    void 보너스_투구_스페어_테스트() {
        // given
        Frame frame = FinalFrame.init();
        Frame frame2 = FinalFrame.init();
        // when
        frame.bowl(BowlingPin.of(3));
        frame.bowl(BowlingPin.of(7));

        frame2.bowl(BowlingPin.of(3));
        frame2.bowl(BowlingPin.of(7));
        frame2.bowl(BowlingPin.of(5));
        // then
        assertThat(frame.isDone()).isFalse();
        assertThat(frame2.isDone()).isTrue();
    }

    @Test
    void 프레임_생성_테스트() {
        // given
        Frame frame = FinalFrame.init();
        // when & then
        Assertions.assertThrows(IllegalStateException.class, () -> frame.next(1));
    }

    @Test
    void 점수_반환_테스트() {
        // given
        Frame normalFrame = FinalFrame.init();
        // when
        normalFrame.bowl(BowlingPin.of(3));
        normalFrame.bowl(BowlingPin.of(5));
        // then
        assertThat(normalFrame.frameState()).isEqualTo("3|5");
    }

    @Test
    void 점수_반환_테스트_2() {
        // given
        Frame normalFrame = FinalFrame.init();
        // when
        normalFrame.bowl(BowlingPin.of(10));
        normalFrame.bowl(BowlingPin.of(3));
        normalFrame.bowl(BowlingPin.of(3));
        // then
        assertThat(normalFrame.frameState()).isEqualTo("X|3|3");
    }

    @Test
    void 점수_반환_테스트_3() {
        // given
        Frame normalFrame = FinalFrame.init();
        // when
        normalFrame.bowl(BowlingPin.of(5));
        normalFrame.bowl(BowlingPin.of(5));
        normalFrame.bowl(BowlingPin.of(10));
        // then
        assertThat(normalFrame.frameState()).isEqualTo("5|/|X");
    }

}
