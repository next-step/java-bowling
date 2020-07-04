package bowling.frame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameCountTest {

    @Test
    @DisplayName("increment 를 호출 할 경우 값이 1 증가한다.")
    void increment() {
        assertThat(FinalFrameCount.of().increment()).isEqualTo(FinalFrameCount.of(1));
    }

    @Test
    @DisplayName("Count 는 최대값을 3으로 가진다.")
    void isMax() {
        assertThat(FinalFrameCount.of(2).isMax()).isFalse();
        assertThat(FinalFrameCount.of(3).isMax()).isTrue();
    }


}
