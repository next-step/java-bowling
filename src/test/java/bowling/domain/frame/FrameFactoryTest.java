package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class FrameFactoryTest {

    @DisplayName("round 가 10일 경우 FinalFrame을 생성한다.")
    @Test
    void createFinalFrameTest() {
        assertThat(FrameFactory.getReadyFrame(Round.from(10))).isEqualTo(FinalFrame.readyFrame());
    }

    @DisplayName("round 가 1~9일 경우 NomalFrame을 생성한다.")
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,5,6,7,8,9})
    void createFinalFrameTest(int round) {
        assertThat(FrameFactory.getReadyFrame(Round.from(round))).isEqualTo(NormalFrame.readyFrame(Round.from(round)));
    }
}
