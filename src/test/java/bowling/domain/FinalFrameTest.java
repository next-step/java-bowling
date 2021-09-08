package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @DisplayName("final frame 에서는 다음 프레임을 만들 수 없다.")
    @Test
    void create_next() {
//        assertThatThrownBy(() -> new FinalFrame(10).nextFrame(10))
//                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("마지막 점수 규칙: 스페어나 스트라이크일 경우 3번의 스코어가 주어진다.")
    @Test
    void one_more_game() {
        // 10 round
        // 2 8 onemore
        // 10 2 onemore
//        assertThat(new FinalFrame(10).next(2)).is
    }
}