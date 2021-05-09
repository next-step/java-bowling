package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.exception.InvalidFirstBowlSizeException;
import bowling.exception.InvalidPinsSizeException;
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
        // given
        Pins pins = Pins.valueOf(9);

        // when
        State firstBowl = FirstBowl.from(pins);

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
        assertThatThrownBy(() -> FirstBowl.from(pins))
                .isInstanceOf(PinsNullPointerException.class)
                .hasMessage("Pins 인스턴스가 null 입니다.");
    }

    @DisplayName("FirstBowl 인스턴스에 10의 값을 가진 Pins 입력시 예외처리 테스트")
    @Test
    void 검증_10값을_가진_Pins() {
        // given
        Pins pins = Pins.valueOf(10);

        // when and then
        assertThatThrownBy(() -> FirstBowl.from(pins))
                .isInstanceOf(InvalidFirstBowlSizeException.class)
                .hasMessage("FirstBowl 에 대해 알맞지 않은 크기가 입력 되었습니다.");
    }

    @DisplayName("FirstBowl 인스턴스가 Spare 를 반환하는지 테스트")
    @Test
    void 반환_bowl_Spare() {
        // given
        Pins pins = Pins.valueOf(9);

        // when
        State firstBowl = FirstBowl.from(pins);
        State actual = firstBowl.bowl(Pins.valueOf(1));

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual).isInstanceOf(Spare.class)
        );
    }

    @DisplayName("FirstBowl 인스턴스가 Miss 를 반환하는지 테스트")
    @Test
    void 반환_bowl_Miss() {
        // given
        Pins pins = Pins.valueOf(9);

        // when
        State firstBowl = FirstBowl.from(pins);
        State actual = firstBowl.bowl(Pins.valueOf(0));

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual).isInstanceOf(Miss.class)
        );
    }

    @DisplayName("이전이 Miss 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Miss_일_경우() {
        // given
        Pins pins = Pins.valueOf(9);

        // when
        State firstBowl = FirstBowl.from(pins);
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
        // given
        Pins pins = Pins.valueOf(9);

        // when
        State firstBowl = FirstBowl.from(pins);
        Score beforeScore = Score.spare();
        Score actual = firstBowl.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + pins.count())
        );
    }

    @DisplayName("이전이 Strike 일 때, calculateAdditionalScore 의 결과로 알맞는 Score 인스턴스를 반한하는지 테스트")
    @Test
    void 반환_calculateAdditionalScore_Strike_일_경우() {
        // given
        Pins pins = Pins.valueOf(9);

        // when
        State firstBowl = FirstBowl.from(pins);
        Score beforeScore = Score.strike();
        Score actual = firstBowl.calculateAdditionalScore(beforeScore);

        // then
        assertAll(
                () -> assertThat(actual.isFinish()).isTrue(),
                () -> assertThat(actual.score()).isEqualTo(beforeScore.score() + pins.count())
        );
    }

}