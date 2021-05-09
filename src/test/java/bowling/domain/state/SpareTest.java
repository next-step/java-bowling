package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.exception.InvalidSpareSizeException;
import bowling.exception.PinsNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SpareTest {

    @DisplayName("Spare 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // given
        Pins firstPins = Pins.valueOf(0);
        Pins secondPins = Pins.valueOf(10);

        // when
        State spare = Spare.of(firstPins, secondPins);

        // then
        assertAll(
                () -> assertThat(spare).isNotNull(),
                () -> assertThat(spare).isInstanceOf(Spare.class)
        );
    }

    @DisplayName("Spare 인스턴스 생성시 null 입력 시 예외처리 테스트")
    @Test
    void 검증_null() {
        // given
        Pins firstPins = null;
        Pins secondPins = null;

        // when and then
        assertThatThrownBy(() -> Spare.of(firstPins, secondPins))
                .isInstanceOf(PinsNullPointerException.class)
                .hasMessage("Pins 인스턴스가 null 입니다.");
    }

    @DisplayName("Spare 인스턴스 생성시 두 핀의 사이즈 합이 10이 아닐 경우 예외처리 테스트")
    @Test
    void 검증_사이즈() {
        // given
        Pins firstPins = Pins.valueOf(1);
        Pins secondPins = Pins.valueOf(10);

        // when and then
        assertThatThrownBy(() -> Spare.of(firstPins, secondPins))
                .isInstanceOf(InvalidSpareSizeException.class)
                .hasMessage("Spare 에 대해 알맞지 않은 크기가 입력 되었습니다.");
    }

    @DisplayName("Spare 인스턴스가 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_score() {
        // given
        Pins firstPins = Pins.valueOf(0);
        Pins secondPins = Pins.valueOf(10);

        // when
        State spare = Spare.of(firstPins, secondPins);

        // then
        assertAll(
                () -> assertThat(spare.score()).isNotNull(),
                () -> assertThat(spare.score()).isInstanceOf(Score.class),
                () -> assertThat(spare.score().score()).isEqualTo(10)
        );
    }

    @DisplayName("이전이 Miss 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Miss_일_경우() {
        // given
        Pins firstPins = Pins.valueOf(1);
        Pins secondPins = Pins.valueOf(9);

        // when
        State spare = Spare.of(firstPins, secondPins);
        Score beforeScore = Score.miss(Pins.valueOf(0));
        Score actual = spare.calculateAdditionalScore(beforeScore);

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
        Pins secondPins = Pins.valueOf(9);

        // when
        State spare = Spare.of(firstPins, secondPins);
        Score beforeScore = Score.spare();
        Score actual = spare.calculateAdditionalScore(beforeScore);

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
        Pins secondPins = Pins.valueOf(9);

        // when
        State spare = Spare.of(firstPins, secondPins);
        Score beforeScore = Score.strike();
        Score actual = spare.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + spare.score().score())
        );
    }

}