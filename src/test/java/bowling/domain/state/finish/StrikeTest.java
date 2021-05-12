package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StrikeTest {

    @DisplayName("Strike 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        State strike = new Strike();

        // then
        assertAll(
                () -> assertThat(strike).isNotNull(),
                () -> assertThat(strike).isInstanceOf(Strike.class)
        );
    }

    @DisplayName("Strike 인스턴스의 Score 반환 여부 테스트")
    @Test
    void 반환_Score() {
        // given
        State strike = new Strike();

        // when
        Score strikeScore = strike.score();

        // then
        assertAll(
                () -> assertThat(strikeScore.isFinish()).isFalse(),
                () -> assertThat(strikeScore.score()).isEqualTo(10)
        );
    }

    @DisplayName("이전이 Miss 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Miss_일_경우() {
        // given
        State strike = new Strike();

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
        State strike = new Strike();

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
        State strike = new Strike();

        // when
        Score beforeScore = Score.strike();
        Score actual = strike.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isFalse(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + strike.score().score())
        );
    }

    @DisplayName("Strike 인스턴스가 알맞는 표기 정보를 반환하는지 테스트")
    @Test
    void 반환_description() {
        // given
        State strike = new Strike();

        // when
        String actual = strike.description();

        // then
        assertThat(actual).isEqualTo("X");
    }
}