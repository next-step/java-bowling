package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
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
        // when
        State spare = new Spare(0, 10);

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
        assertThatThrownBy(() -> new Spare(firstPins, secondPins))
                .isInstanceOf(PinsNullPointerException.class)
                .hasMessage("Pins 인스턴스가 null 입니다.");
    }

    @DisplayName("Spare 인스턴스 생성시 두 핀의 사이즈 합이 10이 아닐 경우 예외처리 테스트")
    @Test
    void 검증_사이즈() {
        // when and then
        assertThatThrownBy(() -> new Spare(1, 10))
                .isInstanceOf(InvalidSpareSizeException.class)
                .hasMessage("Spare 에 대해 알맞지 않은 크기가 입력 되었습니다.");
    }

    @DisplayName("Spare 인스턴스가 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_score() {
        // given
        State spare = new Spare(0, 10);

        // when
        Score spareScore = spare.score();

        // then
        assertAll(
                () -> assertThat(spareScore.isFinish()).isFalse(),
                () -> assertThat(spareScore.score()).isEqualTo(10)
        );
    }

    @DisplayName("이전이 Miss 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Miss_일_경우() {
        // when
        State spare = new Spare(1, 9);
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
        // when
        State spare = new Spare(1, 9);
        Score beforeScore = Score.spare();
        Score actual = spare.calculateAdditionalScore(beforeScore);

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
        State spare = new Spare(1, 9);
        Score spareScore = spare.score();
        Score beforeScore = Score.strike();
        Score actual = spare.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + spareScore.score())
        );
    }

    @DisplayName("Spare 인스턴스가 알맞는 표기 정보를 반환하는지 테스트")
    @Test
    void 반환_description_일반적인_경우() {
        // when
        State spare = new Spare(1, 9);
        String actual = spare.description();

        // given
        assertThat(actual).isEqualTo(1 + "|/");
    }

    @DisplayName("Spare 인스턴스가 gutter 를 알맞게 표기하는지 테스트")
    @Test
    void 반환_description_gutter_있는_경우() {
        // when
        State spare = new Spare(0, 10);
        String actual = spare.description();

        // then
        assertThat(actual).isEqualTo("-|/");
    }
}