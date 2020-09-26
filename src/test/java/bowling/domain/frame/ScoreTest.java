package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("READY 상태는 점수 0점, 추가 투구 3회를 가짐")
    void ready_score_test() {
        assertThat(Score.ofReady()).isEqualTo(Score.of(0, Remaining.of(3)));
    }

    @Test
    @DisplayName("STRIKE 상태는 점수 10점, 추가 투구 2회를 가짐")
    void strike_score_test() {
        assertThat(Score.ofStrike()).isEqualTo(Score.of(10, Remaining.of(2)));
    }

    @Test
    @DisplayName("SPARE 상태는 점수 10점, 추가 투구 1회를 가짐")
    void spare_score_test() {
        assertThat(Score.ofSpare()).isEqualTo(Score.of(10, Remaining.of(1)));
    }

    @Test
    @DisplayName("GUTTER 상태는 점수 0점, 추가 횟수 0을 가짐")
    void gutter_score_test() {
        assertThat(Score.ofGutter()).isEqualTo(Score.of(0, Remaining.of(0)));
    }

    @Test
    @DisplayName("Pending 상태는 점수는 0점, 추가 횟수 3을 가짐")
    void pending_score_test() {
        assertThat(Score.ofPending()).isEqualTo(Score.of(0, Remaining.of(3)));
    }

    @Test
    @DisplayName("pending 상태 테스트")
    void pending_test() {
        assertThat(Score.of(10, Remaining.of(1)).isPending()).isTrue();
        assertThat(Score.of(10, Remaining.of(0)).isPending()).isFalse();
    }

    @Test
    @DisplayName("Score 덧셈 테스트")
    void score_add_test() {
        assertThat(Score.of(10, Remaining.of(1)).add(Score.of(5))).isEqualTo(Score.of(15));
    }

    @Test
    @DisplayName("Score 덧셈시 추가 횟수가 없는 경우 기존 값을 리턴")
    void score_add_remaining_zero_test() {
        assertThat(Score.of(10, Remaining.of(0)).add(Score.of(5))).isEqualTo(Score.of(10));
    }
}
