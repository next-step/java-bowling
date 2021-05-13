package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.exception.InvalidMissSizeException;
import bowling.exception.PinsNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MissTest {

    @DisplayName("Miss 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        State miss = new Miss(9, 0);

        // then
        assertAll(
                () -> assertThat(miss).isNotNull(),
                () -> assertThat(miss).isInstanceOf(Miss.class)
        );
    }

    @DisplayName("Miss 인스턴스 생성시 null 입력 시 예외처리 테스트")
    @Test
    void 검증_null() {
        // given
        Pins firstPins = null;
        Pins secondPins = null;

        // when and then
        assertThatThrownBy(() -> new Miss(firstPins, secondPins))
                .isInstanceOf(PinsNullPointerException.class)
                .hasMessage("Pins 인스턴스가 null 입니다.");
    }

    @DisplayName("Miss 인스턴스 생성시 입력되는 값의 합이 10 이상일시 예외처리 테스트")
    @Test
    void 검증_값의_합이_10미만() {
        // when and then
        assertThatThrownBy(() -> new Miss(9, 1))
                .isInstanceOf(InvalidMissSizeException.class)
                .hasMessage("Miss 에 대해 알맞지 않은 크기가 입력 되었습니다.");
    }

    @DisplayName("Miss 인스턴스가 알맞은 Score 반환하는지 테스트")
    @Test
    void 반환_score() {
        // given
        State miss = new Miss(1, 8);

        // when
        Score missScore = miss.score();

        // then
        assertAll(
                () -> assertThat(missScore.isFinish()).isTrue(),
                () -> assertThat(missScore.score()).isEqualTo(9)
        );

    }

    @DisplayName("이전이 Miss 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Miss_일_경우() {
        // when
        State miss = new Miss(1, 8);
        Score beforeScore = Score.miss(Pins.valueOf(0));
        Score actual = miss.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score())
        );
    }

    @DisplayName("이전이 Spare 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Spare_일_경우() {
        // when
        State miss = new Miss(1, 8);
        Score beforeScore = Score.spare();
        Score actual = miss.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(1 + beforeScore.score())
        );
    }

    @DisplayName("이전이 Strike 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Strike_일_경우() {
        // when
        State miss = new Miss(1, 8);
        Score missScore = miss.score();
        Score beforeScore = Score.strike();
        Score actual = miss.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + missScore.score())
        );
    }

    @DisplayName("Miss 인스턴스가 점수를 알맞게 표기하는지 테스트")
    @Test
    void 반환_description_일반적인_경우() {
        // when
        State miss = new Miss(1, 8);
        String actual = miss.description();

        // then
        assertThat(actual).isEqualTo("1|8");
    }

    @DisplayName("Miss 인스턴스가 gutter 를 알맞게 표기하는지 테스트")
    @Test
    void 반환_description_gutter_있는_경우() {
        // when
        State miss = new Miss(0, 0);
        String actual = miss.description();

        // then
        assertThat(actual).isEqualTo("-|-");
    }

}