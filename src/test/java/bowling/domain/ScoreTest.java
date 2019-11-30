package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("isLeft, isNotLeft 메소드 테스트")
    void isLeft() {
        Score score = Score.ofDefaultScore();

        assertThat(score.isLeft()).isFalse();
        assertThat(score.isNotLeft()).isTrue();
    }

    @Test
    @DisplayName("점수 더했을때 점수가 잘 더해지는지, 보너스 횟수가 줄어드는지 확인한다.")
    void addBonus() {
        Score strike = Score.ofStrike(0);
        Score spare = Score.ofSpare(0);
        Score miss = Score.of(0, 8);

        assertThat(strike.addBonus(Pin.ofAllPin())).isEqualTo(new Score(20, 1));
        assertThat(spare.addBonus(Pin.ofAllPin())).isEqualTo(new Score(20, 0));
        assertThat(miss.addBonus(Pin.ofAllPin())).isEqualTo(new Score(8, 0));
    }
}
