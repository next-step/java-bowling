package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.Pins;
import step2.domain.Score;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    @DisplayName("스페어 점수 얻기")
    void getScore() {
        Pins pins1 = Pins.from(8);
        Pins pins2 = Pins.from(2);
        Spare spare = new Spare(pins1, pins2);

        Score score = Score.of(10, 0);

        assertThat(spare.getScore()).isEqualTo(score);
    }

    @Test
    @DisplayName("추가 점수 구하기")
    void calculateAdditionalScore() {
        Pins pins1 = Pins.from(8);
        Pins pins2 = Pins.from(2);
        Spare spare = new Spare(pins1, pins2);

        Score score = Score.of(0, 2);

        assertThat(spare.calculateAdditionalScore(score).getScore()).isEqualTo(10);
    }

}