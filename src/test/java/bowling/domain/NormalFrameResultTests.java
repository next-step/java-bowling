package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NormalFrameResultTests {
    @DisplayName("첫번째 투구로 맞춘 핀의 수를 입력해서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        int numberOfHitPin = 5;
        NormalFrameResult normalFrameResult = NormalFrameResult.firstThrow(numberOfHitPin);
        assertThat(normalFrameResult.isStrike()).isFalse();
    }
}
