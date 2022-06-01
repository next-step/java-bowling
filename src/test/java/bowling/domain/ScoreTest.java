package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreTest {
    @Test
    void bowl_테스트() {
        assertThat(new Score(5,2).bowl(3).getScore()).isEqualTo(8);
    }
}