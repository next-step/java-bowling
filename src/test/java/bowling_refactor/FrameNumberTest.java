package bowling_refactor;

import bowling_refactor.domain.FrameNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameNumberTest {

    @Test
    @DisplayName("프레임넘버가 1보다 작은 경우")
    void lessThanZeroFrameNumber() {
        assertThatThrownBy(() -> {
            FrameNumber.of(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("프레임넘버가 10보다 큰 경우")
    void moreTenFrameNumber() {
        assertThatThrownBy(() -> {
            FrameNumber.of(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("마지막프레임 테스트")
    void isLast() {
        assertThat(FrameNumber.of(10).isFinalFrame()).isTrue();
    }
}
