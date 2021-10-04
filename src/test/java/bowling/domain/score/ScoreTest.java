package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("strike에 해당하는 score를 생성할 수 있다.")
    void createStrikeTest() {

        // given
        Score expected = Score.from(10, 2);

        // when
        Score result = Score.strike();

        // then
        assertThat(result).isEqualTo(expected);
    }

}