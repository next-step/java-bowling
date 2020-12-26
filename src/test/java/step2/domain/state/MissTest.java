package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.Pins;
import step2.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;

class MissTest {

    @Test
    @DisplayName("미스 점수 얻기")
    void checkMiss() {
        Pins pins1 = Pins.from(3);
        Pins pins2 = Pins.from(4);

        Miss miss = new Miss(pins1, pins2);
        Score score = Score.of(7, 0);

        assertThat(miss.getScore()).isEqualTo(score);
    }

    @Test
    @DisplayName("추가 점수 구하기")
    void calculateAdditionalScore() {
        Pins pins1 = Pins.from(3);
        Pins pins2 = Pins.from(4);

        Miss miss = new Miss(pins1, pins2);
        Score score = Score.of(0, 2);

        assertThat(miss.calculateAdditionalScore(score).getScore()).isEqualTo(7);
    }

}