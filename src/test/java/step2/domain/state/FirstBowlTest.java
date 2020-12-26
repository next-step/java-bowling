package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.Pins;
import step2.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;

class FirstBowlTest {

    @Test
    @DisplayName("두번째 투구 실행시, 스페어")
    void bowlSpare() {
        Pins pins = Pins.from(9);
        State firstBowl = new FirstBowl(pins);

        assertThat(firstBowl.bowl(1)).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("두번째 투구 실행시, 미스")
    void bowlMiss() {
        Pins pins = Pins.from(8);
        State firstBowl = new FirstBowl(pins);

        assertThat(firstBowl.bowl(1)).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("두번째 투구 실행시, 점수 계산")
    void calculateAdditionalScore() {
        Pins pins = Pins.from(8);
        State firstBowl = new FirstBowl(pins);

        Score score = Score.of(0, 2);
        score.bowl(2);

        assertThat(firstBowl.calculateAdditionalScore(score)).isEqualTo(score);
    }

}