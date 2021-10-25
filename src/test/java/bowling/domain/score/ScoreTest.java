package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bowling.exception.score.CannotCalculateException;
import bowling.exception.score.ScoreAddPinException;
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

    @Test
    @DisplayName("현재 score를 반환할 때 남은 횟수가 있으면 exception이 발생되어야 한다.")
    void scoreExceptionTest() {

        // given
        Score score = Score.spare();

        // when & then
        assertThatExceptionOfType(CannotCalculateException.class)
            .isThrownBy(() -> score.score())
            .withMessageMatching("현재 score는 남은 보너스 횟수가 있어 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("Score에 pin을 더할 때 left가 남아있지 않으면 exception이 발생되어야 한다.")
    void scoreAddPinExceptionTest() {

        // given
        Score score = Score.from(3, 0);

        // when & then
        assertThatExceptionOfType(ScoreAddPinException.class)
            .isThrownBy(() -> score.addPin(Pin.of(10)))
            .withMessageMatching("현재 score는 남은 보너스 횟수가 없어 점수를 더할 수 없습니다.");
    }

}