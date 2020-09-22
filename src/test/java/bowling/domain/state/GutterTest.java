package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GutterTest {

    @Test
    @DisplayName("GUTTER 상태에서 공을 굴릴 경우 예외발생 테스트")
    void gutter_throw_exception_test() {
        assertThatThrownBy(() -> Gutter.of().bowl(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("GUTTER 상태는 추가 투구 없이 종료(True) 값을 가지는 테스트")
    void gutter_isEnd_test() {
        assertThat(Gutter.of().isEnd()).isTrue();
    }

    @Test
    @DisplayName("GUTTER 상태는 점수 0점, 추가 투구 0회를 가짐")
    void gutter_getScore_test() {

        // given
        State gutter = Gutter.of();

        // when
        Score score = gutter.getScore();

        // then
        assertThat(score).isEqualTo(Score.of(0, Remaining.of(0)));
    }

    @Test
    @DisplayName("GUTTER 상태는 기존 점수에 0점이 더해져 계산됨")
    void gutter_calculate_test() {

        // given
        Score baseScore = Score.of(10, Remaining.of(2));

        // when
        Score calculateScore = Gutter.of().calculate(baseScore);

        // then
        assertThat(calculateScore).isEqualTo(Score.of(10, Remaining.of(0)));
        assertThat(calculateScore.isPending()).isEqualTo(Boolean.FALSE);
    }
}
