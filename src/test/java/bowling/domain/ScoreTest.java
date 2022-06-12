package bowling.domain;

import bowling.exception.CannotCalculateScore;
import bowling.exception.InvalidScoreCountException;
import bowling.exception.InvalidScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    @DisplayName("점수의 최솟값은 0 점이다.")
    void invalidScore() {
        assertThatThrownBy(() -> Score.of(-1, 2)).isInstanceOf(InvalidScoreException.class);
    }

    @Test
    @DisplayName("추가점수의 횟수의 범위는 0 ~ 2 이다.")
    void invalidScore_count() {
        assertThatThrownBy(() -> Score.of(0, 3)).isInstanceOf(InvalidScoreCountException.class);
        assertThatThrownBy(() -> Score.of(0, -1)).isInstanceOf(InvalidScoreCountException.class);
    }

    @Test
    @DisplayName("스트라이크의 경우 10점과 2번의 추가점수 기회가 있다.")
    void strike() {
        assertThat(Score.strike()).isEqualTo(Score.of(10, 2));
    }

    @Test
    @DisplayName("스페어의 경우 10점과 1번의 추가점수 기회가 있다.")
    void spare() {
        assertThat(Score.spare()).isEqualTo(Score.of(10, 1));
    }

    @Test
    @DisplayName("미스의 경우 첫번째 투구와 두번째 투구의 합의 점수와 추가점수 기회가 없다.")
    void miss() {
        assertThat(Score.miss(Hit.valueOf(3),Hit.valueOf(5))).isEqualTo(Score.of(8, 0));
    }

    @Test
    @DisplayName("보너스의 경우 첫번째 히트 점수와 추가점수 기회가 없다.")
    void bonus() {
        assertThat(Score.bonus(Hit.valueOf(3))).isEqualTo(Score.of(3, 0));
    }

    @Test
    @DisplayName("추가 점수 계산 시 Score 는 Hit 개수만큼 증가하고 additionalScoreCount 는 1 감소한 Score 를 반환한다.")
    void addAdditionalScore() {
        Score strike = Score.strike();
        Hit additionalHit = Hit.valueOf(5);

        Score score = strike.addAdditionalScore(additionalHit);

        assertThat(score).isEqualTo(Score.of(15, 1));
    }

    @Test
    @DisplayName("추가 점수 기회가 없을때 호출 시 예외를 반환한다.")
    void invalidAddAdditionalScore() {
        Score miss = Score.of(7, 0);
        Hit additionalHit = Hit.valueOf(5);

        assertThatThrownBy(() -> miss.addAdditionalScore(additionalHit)).isInstanceOf(CannotCalculateScore.class);
    }
}