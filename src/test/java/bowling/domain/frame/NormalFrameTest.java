package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NormalFrameTest {
    @Test
    void 생성_테스트() {
        // given
        Frame normalFrame = NormalFrame.of(0);
        // when & then
        assertThat(normalFrame).isEqualTo(NormalFrame.of(0));
    }

    @Test
    void 투구_테스트() {
        // given
        Frame normalFrame = NormalFrame.of(0);
        // when & then
        normalFrame.bowl(10);
        assertThat(normalFrame.isDone()).isFalse();
    }

    @Test
    void 다음_프레임_생성_테스트() {
        // given
        Frame normalFrame = NormalFrame.of(0);
        // when & then
        assertThat(normalFrame.next()).isInstanceOf(NormalFrame.class);
    }

}
