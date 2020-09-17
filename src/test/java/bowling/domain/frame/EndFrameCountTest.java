package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EndFrameCountTest {

    @Test
    @DisplayName("마지막 10 프레임은 최대 3개의 값을 가지는 테스트")
    void isMax_test() {
        assertThat(EndFrameCount.of(2).isMax()).isFalse();
        assertThat(EndFrameCount.of(3).isMax()).isTrue();
    }

    @Test
    @DisplayName("EndFrameCount 값 1 증가시키는 테스트")
    void increment_test() {
        assertThat(EndFrameCount.of().increment()).isEqualTo(EndFrameCount.of(1));
    }

}
