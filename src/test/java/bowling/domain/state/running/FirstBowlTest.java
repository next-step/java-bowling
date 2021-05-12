package bowling.domain.state.running;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.exception.InvalidFirstBowlSizeException;
import bowling.exception.PinsNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FirstBowlTest {

    @DisplayName("FirstBowl 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        State firstBowl = new FirstBowl(9);

        // then
        assertAll(
                () -> assertThat(firstBowl).isNotNull(),
                () -> assertThat(firstBowl).isInstanceOf(FirstBowl.class)
        );
    }

    @DisplayName("FirstBowl 인스턴스에 null 입력시 예외처리 테스트")
    @Test
    void 검증_null() {
        // given
        Pins pins = null;

        // when and then
        assertThatThrownBy(() -> new FirstBowl(pins))
                .isInstanceOf(PinsNullPointerException.class)
                .hasMessage("Pins 인스턴스가 null 입니다.");
    }

    @DisplayName("FirstBowl 인스턴스에 10의 값을 가진 Pins 입력시 예외처리 테스트")
    @Test
    void 검증_10값을_가진_Pins() {
        // when and then
        assertThatThrownBy(() -> new FirstBowl(10))
                .isInstanceOf(InvalidFirstBowlSizeException.class)
                .hasMessage("FirstBowl 에 대해 알맞지 않은 크기가 입력 되었습니다.");
    }

    @DisplayName("FirstBowl 인스턴스가 Spare 를 반환하는지 테스트")
    @Test
    void 반환_bowl_Spare() {
        // when
        State firstBowl = new FirstBowl(9);
        State actual = firstBowl.bowl(1);

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual).isInstanceOf(Spare.class)
        );
    }

    @DisplayName("FirstBowl 인스턴스가 Miss 를 반환하는지 테스트")
    @Test
    void 반환_bowl_Miss() {
        // when
        State firstBowl = new FirstBowl(9);
        State actual = firstBowl.bowl(0);

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual).isInstanceOf(Miss.class)
        );
    }

    @DisplayName("이전이 Miss 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Miss_일_경우() {
        // when
        State firstBowl = new FirstBowl(9);
        Score beforeScore = Score.miss(Pins.valueOf(0));
        Score actual = firstBowl.calculateAdditionalScore(beforeScore);

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
        State firstBowl = new FirstBowl(9);
        Score beforeScore = Score.spare();
        Score actual = firstBowl.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + 9)
        );
    }

    @DisplayName("이전이 Strike 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Strike_일_경우() {
        // when
        State firstBowl = new FirstBowl(9);
        Score beforeScore = Score.strike();
        Score actual = firstBowl.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isFalse(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + 9)
        );
    }

    @DisplayName("FirstBowl 인스턴스가 점수를 알맞게 표기하는지 테스트")
    @Test
    void 반환_description() {
        // when
        State zeroFirstBowl = new FirstBowl(0);
        State generalFirstBowl = new FirstBowl(9);

        // then
        assertAll(
                () -> assertThat(zeroFirstBowl.description()).isEqualTo("-"),
                () -> assertThat(generalFirstBowl.description()).isEqualTo("9")
        );
    }

}