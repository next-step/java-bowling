package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Spare 테스트")
class SpareTest {

    @DisplayName("Spare 인 상황에서 공을 굴리는 경우 예외가 발생한다.")
    @Test
    void bowlException() {

        final Spare spare = new Spare(new Pin(0), new Pin(10));

        assertThatThrownBy(() -> spare.bowl(new Pin(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Spare 는 점수를 1번 더해야 계산이 가능하다.")
    @Test
    void calculate() {

        final Spare beforeSpare = new Spare(new Pin(8), new Pin(2));

        final Score result = new Strike().calculateAdditionalScore(beforeSpare.getScore());

        assertThat(result.canCalculateScore()).isTrue();
    }
}
