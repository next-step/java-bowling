package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("스트라이크 테스트")
class StrikeTest {

    @DisplayName("Strike 인 상황에서 공을 굴리는 경우 예외가 발생한다.")
    @Test
    void bowlException() {

        assertThatThrownBy(() -> new Strike().bowl(new Pin(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Strike 는 점수를 2번 더해야 계산이 가능하다.")
    @Test
    void calculate() {

        final Strike beforeStrike = new Strike();
        final Strike currentStrike = new Strike();

        final Score beforeScore = currentStrike.calculateAdditionalScore(beforeStrike.getScore());
        final Score result = currentStrike.calculateAdditionalScore(beforeScore);

        assertThat(result.canCalculateScore()).isTrue();
    }
}
