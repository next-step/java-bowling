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

    @Test
    @DisplayName("spare에 해당하는 score를 생성할 수 있다.")
    void createSpareTest() {

        // given
        Score expected = Score.from(10, 1);

        // when
        Score result = Score.spare();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("miss에 해당하는 score를 생성할 수 있다.")
    void createMissTest() {

        // given
        Pin first = Pin.of(3);
        Pin second = Pin.of(4);
        Score expected = Score.from(7, 0);

        // when
        Score result = Score.miss(first.sum(second));

        // then
        assertThat(result).isEqualTo(expected);
    }

}