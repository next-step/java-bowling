package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FinalFrameTest {
    @Test
    @DisplayName(" 생성 테스트")
    void init() {
        assertThatCode(() -> FinalFrame.newInstance()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("스트라이크나 스페어인 경우 투구 기회가 3번")
    void bowl() {
        Frame frame = FinalFrame.newInstance();
        assertThatCode(() -> frame.bowl(10).bowl(10).bowl(10)).doesNotThrowAnyException();
        assertThatIllegalStateException().isThrownBy(()-> frame.bowl(1).bowl(3).bowl(10));
    }

}
