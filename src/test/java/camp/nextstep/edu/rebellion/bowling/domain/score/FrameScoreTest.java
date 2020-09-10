package camp.nextstep.edu.rebellion.bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FrameScoreTest {
    @DisplayName("프레임 점수가 잘 생성되는지 확인")
    @Test
    public void frameScoreTest() {
        // given
        FrameScore score = NormalFrameScore.clear();
        int first = 1;
        int last = 2;

        // when
        score.markFirst(first);
        score.markLast(last);

        // then
        assertAll(
                () -> assertThat(score.getFirstScore()).isEqualTo(first),
                () -> assertThat(score.getLastScore()).isEqualTo(last),
                () -> assertThat(score.isStrike()).isFalse(),
                () -> assertThat(score.isSpare()).isFalse()
        );
    }

    @DisplayName("프레임 점수가 Strike 일 경우 확인")
    @Test
    public void isStrike() {
        // given
        FrameScore score = NormalFrameScore.clear();
        int first = 10;

        // when
        score.markFirst(first);

        // then
        assertAll(
                () -> assertThat(score.getFirstScore()).isEqualTo(first),
                () -> assertThat(score.isStrike()).isTrue(),
                () -> assertThat(score.isSpare()).isFalse()
        );
    }

    @DisplayName("프레임 점수가 Spare 일 경우 확인")
    @Test
    public void isSpare() {
        // given
        FrameScore score = NormalFrameScore.clear();
        int first = 9;
        int last = 1;

        // when
        score.markFirst(first);
        score.markLast(last);

        // then
        assertAll(
                () -> assertThat(score.getFirstScore()).isEqualTo(first),
                () -> assertThat(score.getLastScore()).isEqualTo(last),
                () -> assertThat(score.isStrike()).isFalse(),
                () -> assertThat(score.isSpare()).isTrue()
        );
    }

    @DisplayName("프레임 점수가 10을 넘는 경우 예외 발생")
    @Test
    public void scoreRangeThrownTest() {
        // given
        FrameScore score = NormalFrameScore.clear();
        int first = 8;
        int last = 3;

        // when
        score.markFirst(first);

        // then
        assertThatThrownBy(() -> score.markLast(last))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최대 10 점을 넘을 수 없습니다");
    }

    @DisplayName("보너스 프레임 점수가 20을 넘는 경우 예외 발생")
    @Test
    public void bonusScoreRangeThrownTest() {
        // given
        FrameScore score = BonusFrameScore.clear();
        int first = 10;
        int last = 15;

        // when
        score.markFirst(first);

        // then
        assertThatThrownBy(() -> score.markLast(last))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최대 20 점을 넘을 수 없습니다");
    }

    @DisplayName("프레임 점수에 따라 추가 기회가 잘 반영 되는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "10:0:2",
            "9:1:1",
            "3:4:0"
    }, delimiter = ':')
    public void getTryAttemptTest(int first, int last, int attempt) {
        // given
        FrameScore score = NormalFrameScore.clear();

        // when
        score.markFirst(first);
        score.markLast(last);

        // then
        assertThat(score.getTryAttempt()).isEqualTo(attempt);

    }
}
