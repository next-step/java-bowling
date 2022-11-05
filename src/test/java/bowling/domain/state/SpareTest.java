package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("스패어 테스트")
public class SpareTest {

    @DisplayName("공을 굴리는 경우 예외가 발생한다.")
    @Test
    void bowlException() {
        Spare spare = new Spare(Pin.of(0), Pin.of(10));

        assertThatThrownBy(() -> spare.bowl(Pin.of(1)));
    }

    @DisplayName("점수를 1번 더해야 계산이 가능하다.")
    @Test
    void calculate() {
        Spare beforeSpare = new Spare(Pin.of(8), Pin.of(2));
        Strike currentStrike = new Strike();

        Score result = currentStrike.calculateAdditionalScore(beforeSpare.getScore());

        assertThat(result.canCalculateScore()).isTrue();
    }
}
