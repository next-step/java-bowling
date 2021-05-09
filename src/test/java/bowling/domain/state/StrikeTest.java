package bowling.domain.state;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StrikeTest {

    @DisplayName("Strike 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        State strike = Strike.initialize();

        assertAll(
                () -> assertThat(strike).isNotNull(),
                () -> assertThat(strike).isInstanceOf(Strike.class)
        );
    }

    @DisplayName("Strike 인스턴스의 Score 반환 여부 테스트")
    @Test
    void 반환_Score() {
        State strike = Strike.initialize();

        assertAll(
                () -> assertThat(strike.score()).isNotNull(),
                () -> assertThat(strike.score()).isInstanceOf(Score.class),
                () -> assertThat(strike.score().isFinish()).isFalse(),
                () -> assertThat(strike.score().score()).isEqualTo(10)
        );
    }

    @DisplayName("이전이 Miss 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Miss_일_경우() {
        // given
        State strike = Strike.initialize();

        // when
        Score beforeScore = Score.miss(Pins.valueOf(0));
        Score actual = strike.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score())
        );
    }

    @DisplayName("이전이 Spare 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Spare_일_경우() {
        // given
        State strike = Strike.initialize();

        // when
        Score beforeScore = Score.spare();
        Score actual = strike.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + strike.score().score())
        );
    }

    @DisplayName("이전이 Strike 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Strike_일_경우() {
        // given
        State strike = Strike.initialize();

        // when
        Score beforeScore = Score.strike();
        Score actual = strike.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isFalse(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + strike.score().score())
        );
    }
}