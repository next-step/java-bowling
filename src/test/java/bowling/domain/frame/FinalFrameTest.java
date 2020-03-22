package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    @DisplayName("객체 생성 비교")
    void compareToFinalFrame() {
        // give
        FinalFrame actualFinalFrame = new FinalFrame(10);
        FinalFrame expectedFinalFrame = new FinalFrame(10);
        // when
        boolean same = actualFinalFrame.equals(expectedFinalFrame);
        // then
        assertThat(same).isTrue();
    }
}
