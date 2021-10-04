package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.exception.state.MissStateCrerateException;
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

    @Test
    @DisplayName("miss 생성시 pin이 10개 이상 들어오면 exception이 발생해야 한다.")
    void createMissExceptionTest() {

        // given
        Pin first = Pin.of(5);
        Pin second = Pin.of(6);

        // when & then
        assertThatExceptionOfType(MissStateCrerateException.class)
            .isThrownBy(() -> Score.miss(first.sum(second)))
            .withMessageMatching("Miss 상태는 두 핀의 합이 10이 넘을 수 없다.");
    }

    @Test
    @DisplayName("현재 score가 반환할 수 있는지 확인할 수 있다.")
    void canCalculateScoreTest() {

        // given
        Score score = Score.from(7, 0);

        // when
        boolean result = score.canCalculateScore();

        // then
        assertTrue(result);
    }

}