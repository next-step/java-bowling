package bowlingRefactor;

import bowlingRefactor.domain.FrameNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameNumberTest {

    @Test
    @DisplayName("프레임 범위 에러 테스트")
    void exception() {
        assertThatThrownBy(() -> {
            FrameNumber.of(0);
            FrameNumber.of(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("마지막프레임 테스트")
    void isLast() {
        assertThat(FrameNumber.of(10).isFinalFrame()).isTrue();
    }
}
