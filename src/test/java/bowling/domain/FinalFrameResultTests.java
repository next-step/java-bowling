package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameResultTests {
    @DisplayName("맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        int numberOfHitPin = 5;

        FinalFrameResult finalFrameResult = FinalFrameResult.firstBowl(numberOfHitPin);
        assertThat(finalFrameResult.isCompleted()).isFalse();
        assertThat(finalFrameResult.isFinalFrame()).isTrue();
    }
}
