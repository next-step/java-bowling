package bowling.domain.frame;

import bowling.domain.engine.Frame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameFactoryTest {

    @DisplayName("Factory NormalFame 생성 테스트")
    @Test
    void testCase1() {
        // when
        NormalFrame first = FrameFactory.first();
        // then
        assertThat(first).isInstanceOf(NormalFrame.class);
    }

    @DisplayName("Factory FinalFrame 생성 테스트")
    @Test
    void testCase2() {
        // when
        Frame next = FrameFactory.next(9);
        // then
        assertThat(next).isInstanceOf(FinalFrame.class);
    }
}
