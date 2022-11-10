package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("스트라이크 테스트")
public class StrikeTest {

    @DisplayName("공을 굴리는 경우 예외가 발생한다.")
    @Test
    void bowlException() {
        Strike strike = new Strike();

        assertThatThrownBy(() -> strike.bowl(Pin.of(1)));
    }

    @DisplayName("점수를 2번 더해야 계산이 가능하다.")
    @Test
    void calculate() {
        Strike beforeStrike = new Strike();
        Strike currentStrike = new Strike();

        Score beforeScore = currentStrike.calculateAdditionalScore(beforeStrike.getScore());
        Score result = currentStrike.calculateAdditionalScore(beforeScore);

        assertThat(result.canCalculateScore()).isTrue();
    }
}
