package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinalFrameTest {

    @Test
    void 생성_테스트() {
        // given
        Frame finalFrame = FinalFrame.init();
        // when & then
        assertThat(finalFrame).isEqualTo(FinalFrame.of(0));
        assertThat(finalFrame).isEqualTo(FinalFrame.init());
    }

    @Test
    void 투구_2회_테스트() {
        // given
        Frame frame = FinalFrame.init();
        Frame frame2 = FinalFrame.init();
        // when
        frame.bowl(3);
        frame.bowl(5);

        frame2.bowl(3);

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
        frame.bowl(10);

        frame2.bowl(10);
        frame2.bowl(7);

        frame3.bowl(10);
        frame3.bowl(7);
        frame3.bowl(10);
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
        frame.bowl(3);
        frame.bowl(7);

        frame2.bowl(3);
        frame2.bowl(7);
        frame2.bowl(5);
        // then
        assertThat(frame.isDone()).isFalse();
        assertThat(frame2.isDone()).isTrue();
    }

    @Test
    void 프레임_생성_테스트() {
        // given
        Frame frame = FinalFrame.init();
        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> frame.next());
    }
}
