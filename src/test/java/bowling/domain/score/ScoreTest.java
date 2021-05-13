package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreTest {

    @DisplayName("Score 인스턴스가 miss 를 나타내는 인스턴스를 반환하는지 테스트")
    @Test
    void 생성_miss() {
        // when
        Score score = Score.miss(9);

        // then
        assertAll(
                () -> assertThat(score).isNotNull(),
                () -> assertThat(score.isFinish()).isTrue()
        );
    }

    @DisplayName("Score 인스턴스가 spare 를 나타내는 인스턴스를 반환하는지 테스트")
    @Test
    void 생성_spare() {
        // when
        Score score = Score.spare();

        // then
        assertAll(
                () -> assertThat(score).isNotNull(),
                () -> assertThat(score.isFinish()).isFalse()
        );
    }


    @DisplayName("Score 인스턴스가 strike 를 나타내는 인스턴스를 반환하는지 테스트")
    @Test
    void 생성_strike() {
        // when
        Score score = Score.strike();

        // then
        assertAll(
                () -> assertThat(score).isNotNull(),
                () -> assertThat(score.isFinish()).isFalse()
        );
    }

    @DisplayName("Score 인스턴스가 unavailable 를 나타내는 인스턴스를 반환하는지 테스트")
    @Test
    void 생성_unavailable() {
        // when
        Score score = Score.unavailable();

        // then
        assertAll(
                () -> assertThat(score).isNotNull(),
                () -> assertThat(score.isFinish()).isTrue()
        );

    }

    @DisplayName("Score 인스턴스가 보너스 점수를 다 더했는지를 반환하는 테스트")
    @Test
    void 반환_finish() {
        // when
        Score noneBonusScore = Score.miss(9);

        // then
        assertThat(noneBonusScore.isFinish()).isTrue();
    }

    @DisplayName("Score 인스턴스가 보너스 점수를 더하는 기능 테스트")
    @Test
    void 반환_addBonusScore() {
        // given
        Score target = Score.spare();

        // when
        Score score = target.addBonusScore(10);

        // then
        assertAll(
                () -> assertThat(score).isNotNull(),
                () -> assertThat(score.isFinish()).isTrue()
        );

    }

    @DisplayName("Score 인스턴스가 점수를 반환하는 기능 테스트")
    @Test
    void 반환_score() {
        // given
        int allClear = 10;

        // when
        Score miss = Score.miss(9);
        Score spare = Score.spare();
        Score strike = Score.strike();

        // then
        assertAll(
                () -> assertThat(miss.score()).isEqualTo(9),
                () -> assertThat(spare.score()).isEqualTo(allClear),
                () -> assertThat(strike.score()).isEqualTo(allClear)
        );

    }

}