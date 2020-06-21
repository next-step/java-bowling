package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTests {
    @DisplayName("첫 투구를 입력받아서 객체를 생성할 수 있다")
    @Test
    void createTest() {
        int numberOfHitPin = 5;
        FinalFrame finalFrame = FinalFrame.firstBowl(numberOfHitPin);

        assertThat(finalFrame).isNotNull();
    }
}
