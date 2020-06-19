package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTests {
    @DisplayName("첫번째 투구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTEst() {
        int numberOfHitPin = 5;
        FrameResult expectResult = FrameResultFactory.create(numberOfHitPin);

        assertThat(Frame.bowlFirst(numberOfHitPin)).isEqualTo(new Frame(expectResult, null));
    }
}
