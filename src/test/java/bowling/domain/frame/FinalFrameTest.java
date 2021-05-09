package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.States;
import bowling.domain.state.running.Ready;
import bowling.exception.NoActionBowlException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    @DisplayName("FinalFrame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frame finalFrame = FinalFrame.initialize();

        // then
        assertAll(
                () -> assertThat(finalFrame).isNotNull(),
                () -> assertThat(finalFrame).isInstanceOf(FinalFrame.class)
        );
    }

    @DisplayName("FinalFrame 인스턴스가 알맞는 score 반환 여부 테스트")
    @Test
    void 반환_score() {
        // when
        Frame finalFrame = FinalFrame.initialize();

        // then
        assertAll(
                () -> assertThat(finalFrame).isNotNull(),
                () -> assertThat(finalFrame).isInstanceOf(FinalFrame.class)
        );
    }

    @DisplayName("miss 일때, FinalFrame 인스턴스가 bowl 실행 및 종료 여부 테스트")
    @Test
    void 투구_보너스_없을때_miss() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(0);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame = finalFrame.bowl(firstPins);
        finalFrame = finalFrame.bowl(secondPins);

        // then
        assertThat(finalFrame.isFinish()).isTrue();
    }

    @DisplayName("spare 일때, FinalFrame 인스턴스가 2라운드 까지의 bowl 실행 및 종료 여부 테스트")
    @Test
    void 투구_보너스_있을때_spare_2라운드까지_false() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(1);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);

        assertThat(finalFrame.isFinish()).isFalse();
    }

    @DisplayName("spare 일때, FinalFrame 인스턴스가 3라운드 까지의 bowl 실행 및 종료 여부 테스트")
    @Test
    void bowl_보너스_있을때_spare_3라운드까지_true() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(1);
        Pins thirdPins = Pins.valueOf(10);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        // then
        assertThat(finalFrame.isFinish()).isTrue();
    }

    @DisplayName("보너스 없을때, bowl 3회 실행 시 예외처리 테스트")
    @Test
    void bowl_보너스_없을때_예외처리() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(0);
        Pins thirdPins = Pins.valueOf(10);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);

        // then
        assertThatThrownBy(() -> finalFrame.bowl(thirdPins))
                .isInstanceOf(NoActionBowlException.class)
                .hasMessage("현재 상태에서는 더 이상 볼을 던질 수 없습니다.");
    }

    @DisplayName("보너스 있을때, bowl 4회 실행 시 예외처리 테스트")
    @Test
    void bowl_보너스_있을때_예외처리() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(1);
        Pins thirdPins = Pins.valueOf(10);
        Pins fourthPins = Pins.valueOf(10);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        // then
        assertThatThrownBy(() -> finalFrame.bowl(fourthPins))
                .isInstanceOf(NoActionBowlException.class)
                .hasMessage("현재 상태에서는 더 이상 볼을 던질 수 없습니다.");
    }

    @DisplayName("miss 일 때, FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_miss() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(0);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);

        // then
        assertAll(
                () -> assertThat(finalFrame.score()).isNotNull(),
                () -> assertThat(finalFrame.score()).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.score().score()).isEqualTo(firstPins.count() + secondPins.count())
        );

    }

    @DisplayName("spare and firstBowl 일 때, FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_spare_and_firstBowl() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(1);
        Pins thirdPins = Pins.valueOf(9);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        // then
        assertAll(
                () -> assertThat(finalFrame.score()).isNotNull(),
                () -> assertThat(finalFrame.score()).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.score().score()).isEqualTo(19)
        );
    }


    @DisplayName("spare and strike 일 때, FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_spare_and_strike() {
        // given
        Pins firstPins = Pins.valueOf(9);
        Pins secondPins = Pins.valueOf(1);
        Pins thirdPins = Pins.valueOf(10);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        // then
        assertAll(
                () -> assertThat(finalFrame.score()).isNotNull(),
                () -> assertThat(finalFrame.score()).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.score().score()).isEqualTo(20)
        );
    }


    @DisplayName("strike_and_miss 일때, FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_strike_and_miss() {
        // given
        Pins firstPins = Pins.valueOf(10);
        Pins secondPins = Pins.valueOf(9);
        Pins thirdPins = Pins.valueOf(0);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        // then
        assertAll(
                () -> assertThat(finalFrame.score()).isNotNull(),
                () -> assertThat(finalFrame.score()).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.score().score()).isEqualTo(19)
        );

    }

    @DisplayName("strike_and_spare 일때, FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_strike_and_spare() {
        // given
        Pins firstPins = Pins.valueOf(10);
        Pins secondPins = Pins.valueOf(9);
        Pins thirdPins = Pins.valueOf(1);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        // then
        assertAll(
                () -> assertThat(finalFrame.score()).isNotNull(),
                () -> assertThat(finalFrame.score()).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.score().score()).isEqualTo(20)
        );

    }

    @DisplayName("strike_and_strike 일때, FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_strike_and_strike_and_firstBowl() {
        // given
        Pins firstPins = Pins.valueOf(10);
        Pins secondPins = Pins.valueOf(10);
        Pins thirdPins = Pins.valueOf(9);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        // then
        assertAll(
                () -> assertThat(finalFrame.score()).isNotNull(),
                () -> assertThat(finalFrame.score()).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.score().score()).isEqualTo(29)
        );

    }

    @DisplayName("strike_and_strike 일때, FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score_strike_and_strike_and_strike() {
        // given
        Pins firstPins = Pins.valueOf(10);
        Pins secondPins = Pins.valueOf(10);
        Pins thirdPins = Pins.valueOf(10);

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(firstPins);
        finalFrame.bowl(secondPins);
        finalFrame.bowl(thirdPins);

        // then
        assertAll(
                () -> assertThat(finalFrame.score()).isNotNull(),
                () -> assertThat(finalFrame.score()).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.score().score()).isEqualTo(30)
        );

    }
}