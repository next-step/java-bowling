package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
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
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(0);

        State miss = Miss.of(firstPins, secondPins);

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
        assertThatThrownBy(() -> Miss.of(firstPins, secondPins))
                .isInstanceOf(PinsNullPointerException.class)
                .hasMessage("Pins 인스턴스가 null 입니다.");
    }

    @DisplayName("Miss 인스턴스 생성시 입력되는 값의 합이 10 이상일시 예외처리 테스트")
    @Test
    void 검증_값의_합이_10미만() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(1);

        // when and then
        assertThatThrownBy(() -> Miss.of(firstPins, secondPins))
                .isInstanceOf(InvalidMissSizeException.class)
                .hasMessage("Miss 에 대해 알맞지 않은 크기가 입력 되었습니다.");
    }

    @DisplayName("Miss 인스턴스가 알맞은 Score 반환하는지 테스트")
    @Test
    void 반환_score() {
        // given
        Pins firstPins = Pins.valueOf(1);
        Pins secondPins = Pins.valueOf(8);

        // when
        State miss = Miss.of(firstPins, secondPins);

        // then
        assertAll(
                () -> assertThat(miss.score()).isNotNull(),
                () -> assertThat(miss.score()).isInstanceOf(Score.class),
                () -> assertThat(miss.score().isFinish()).isTrue(),
                () -> assertThat(miss.score().score()).isEqualTo(firstPins.count() + secondPins.count())
        );

    }

    @DisplayName("이전이 Miss 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Miss_일_경우() {
        // given
        Pins firstPins = Pins.valueOf(1);
        Pins secondPins = Pins.valueOf(8);

        // when
        State miss = Miss.of(firstPins, secondPins);
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
        // given
        Pins firstPins = Pins.valueOf(1);
        Pins secondPins = Pins.valueOf(8);

        // when
        State miss = Miss.of(firstPins, secondPins);
        Score beforeScore = Score.spare();
        Score actual = miss.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + firstPins.count())
        );
    }

    @DisplayName("이전이 Strike 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Strike_일_경우() {
        // given
        Pins firstPins = Pins.valueOf(1);
        Pins secondPins = Pins.valueOf(8);

        // when
        State miss = Miss.of(firstPins, secondPins);
        Score beforeScore = Score.strike();
        Score actual = miss.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + miss.score().score())
        );
    }

}