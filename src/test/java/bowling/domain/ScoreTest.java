package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-28 01:55
 */
public class ScoreTest {

    @DisplayName("점수 생성 조건 - Strkie 투구")
    @Test
    void 스트라이크_투구_점수_생성() {
        Score score = Score.ofStrike();
        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(10),
                () -> assertThat(score.remainCalculate()).isTrue()
        );
    }

    @DisplayName("점수 생성 조건 - Spare 투구")
    @Test
    void 스페어_투구_점수_생성() {
        Score score = Score.ofSpare();
        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(10),
                () -> assertThat(score.remainCalculate()).isTrue()
        );
    }

    @DisplayName("점수 생성 조건 - Miss 투구")
    @Test
    void 미스_투구_점수_생성() {
        Score score = Score.ofMiss(3);
        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(3),
                () -> assertThat(score.remainCalculate()).isFalse()
        );
    }

    @DisplayName("점수 업데이트")
    @Test
    void 투구_점수_업데이트() {
        Score score = Score.ofStrike();
        assertAll(
                () -> assertThat(score.getScore()).isEqualTo(10),
                () -> assertThat(score.remainCalculate()).isTrue(),
                () -> assertThat(score.calculate(Score.ofMiss(3)).getScore()).isEqualTo(13)
        );
    }
}
