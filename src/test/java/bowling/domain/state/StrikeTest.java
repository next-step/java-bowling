package bowling.domain.state;

import bowling.domain.frame.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @DisplayName("Strike viewString()은 X를 반환한다.")
    @Test
    void viewStringTest() {
        assertThat(new Strike().viewString()).isEqualTo("X");
    }

    @DisplayName("score()은 Pin의 갯수의 합과 시도횟수 2번을 반환한다..")
    @Test
    void scoreTest() {
        assertThat(new Strike().score()).isEqualTo(Score.of(10, 2));
    }

    @DisplayName("calculateAdditionalScore() 10점을 더해서 반환한다.")
    @Test
    void calculateAdditionalScoreTest() {
        assertThat(new Strike().calculateAdditionalScore(Score.of(5, 1)))
                .isEqualTo(Score.of(15, 0));
    }
}
