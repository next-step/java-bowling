package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {

    @Test
    @DisplayName("STRIKE 상태에서는 더 이상 공을 굴릴 수 없는 테스트")
    void strike_throw_exception_test() {
        assertThatThrownBy(() -> Strike.of().bowl(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("STRIKE 상태는 추가 투구 없이 종료(True) 값을 가지는 테스트")
    void strike_isEnd_test() {
        assertThat(Strike.of().isEnd()).isTrue();
    }

    @Test
    @DisplayName("STRIKE 상태는 점수 10점, 추가 투구 2회를 가짐")
    void strike_getScore_test() {

        // given
        State strike = Strike.of();

        // when
        Score score = strike.getScore();

        // then
        assertThat(score).isEqualTo(Score.of(10, Remaining.of(2)));
    }

    @Test
    @DisplayName("STRIKE 상태는 기존 점수에 10점이 더해짐")
    void strike_calculate_test() {

        // given
        Score baseScore = Score.of(10, Remaining.of(2));

        // when
        Score calculateScore = Strike.of().calculate(baseScore);

        // then
        assertThat(calculateScore).isEqualTo(Score.of(20, Remaining.of(1)));
        assertThat(calculateScore.isPending()).isEqualTo(Boolean.TRUE);
    }

}
