package bowling.domain.state;


import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {

    @Test
    @DisplayName("MISS 상태에서 공을 굴릴 경우 예외발생 테스트")
    void miss_throw_exception() {
        assertThatThrownBy(() -> Miss.of(Pin.of(3), Pin.of(6)).bowl(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("MISS 상태는 추가 투구 없이 종료(True) 값을 가지는 테스트")
    void miss_isEnd_test() {
        assertThat(Miss.of(Pin.of(3), Pin.of(6)).isEnd()).isTrue();
    }

    @Test
    @DisplayName("MISS 상태는 두수의 합을 점수로 가지고 추가 투구 0회를 가짐")
    void miss_getScore_test() {

        // given
        State miss = Miss.of(Pin.of(3), Pin.of(4));

        // when
        Score score = miss.getScore();

        // then
        assertThat(score).isEqualTo(Score.of(7, Remaining.of(0)));
    }

    @Test
    @DisplayName("MISS 상태는 기존 점수에 두수의 합이 더해짐")
    void miss_calculate_test() {

        // given
        Score baseScore = Score.of(10, Remaining.of(2));

        // when
        Score calculateScore = Miss.of(Pin.of(3), Pin.of(2)).calculate(baseScore);

        // then
        assertThat(calculateScore).isEqualTo(Score.of(15, Remaining.of(0)));
        assertThat(calculateScore.isPending()).isEqualTo(Boolean.FALSE);
    }
}
