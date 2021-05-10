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

    @DisplayName("beforeScore 가 miss 이고 FinalFrame 인스턴스가 miss일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_miss_finalFrame_miss() {
        // given
        Pins first = Pins.valueOf(9);
        Pins second = Pins.valueOf(0);
        Score miss = Score.miss(Pins.valueOf(9));

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(miss)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(miss)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(miss).score()).isEqualTo(9)
        );

    }

    @DisplayName("beforeScore 가 spare 이고 FinalFrame 인스턴스가 miss일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_spare_finalFrame_miss() {
        // given
        Pins first = Pins.valueOf(9);
        Pins second = Pins.valueOf(0);
        Score spare = Score.spare();

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(spare)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(spare)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(spare).score()).isEqualTo(19)
        );

    }

    @DisplayName("beforeScore 가 strike 이고 FinalFrame 인스턴스가 miss일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_strike_finalFrame_miss() {
        // given
        Pins first = Pins.valueOf(9);
        Pins second = Pins.valueOf(0);
        Score strike = Score.strike();

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(strike)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(strike)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(strike).score()).isEqualTo(19)
        );

    }

    @DisplayName("beforeScore 가 miss 이고 States 인스턴스가 spare일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_miss_finalFrame_spare() {
        // given
        Pins first = Pins.valueOf(9);
        Pins second = Pins.valueOf(1);
        Pins third = Pins.valueOf(10);
        Score miss = Score.miss(Pins.valueOf(9));

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        finalFrame.bowl(third);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(miss)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(miss)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(miss).score()).isEqualTo(9)
        );

    }

    @DisplayName("beforeScore 가 spare 이고 FinalFrame 인스턴스가 spare일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_spare_finalFrame_spare() {
        // given
        Pins first = Pins.valueOf(9);
        Pins second = Pins.valueOf(1);
        Pins third = Pins.valueOf(10);
        Score spare = Score.spare();

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        finalFrame.bowl(third);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(spare)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(spare)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(spare).score()).isEqualTo(20)
        );

    }


    @DisplayName("beforeScore 가 strike 이고 FinalFrame 인스턴스가 spare일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_strike_finalFrame_spare() {
        // given
        Pins first = Pins.valueOf(9);
        Pins second = Pins.valueOf(1);
        Pins third = Pins.valueOf(10);
        Score strike = Score.strike();

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        finalFrame.bowl(third);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(strike)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(strike)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(strike).score()).isEqualTo(20)
        );

    }


    @DisplayName("beforeScore 가 miss 이고 FinalFrame 인스턴스가 strike일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_miss_finalFrame_strike() {
        // given
        Pins first = Pins.valueOf(10);
        Pins second = Pins.valueOf(1);
        Pins third = Pins.valueOf(9);
        Score miss = Score.miss(Pins.valueOf(9));

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        finalFrame.bowl(third);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(miss)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(miss)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(miss).score()).isEqualTo(9)
        );


    }

    @DisplayName("beforeScore 가 spare 이고 States 인스턴스가 strike일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_spare_states_strike() {
        // given
        Pins first = Pins.valueOf(10);
        Pins second = Pins.valueOf(1);
        Pins third = Pins.valueOf(9);
        Score spare = Score.spare();

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        finalFrame.bowl(third);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(spare)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(spare)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(spare).score()).isEqualTo(20)
        );

    }

    @DisplayName("beforeScore 가 strike 이고 States 인스턴스가 strike일 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_before_strike_states_strike() {
        // given
        Pins first = Pins.valueOf(10);
        Pins second = Pins.valueOf(1);
        Pins third = Pins.valueOf(9);
        Score strike = Score.strike();

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        finalFrame.bowl(third);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(strike)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(strike)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(strike).score()).isEqualTo(21)
        );

    }

    @DisplayName("모두 strike인 경우 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_calculateAdditionalScore_strike_strike_strike() {
        // given
        Pins first = Pins.valueOf(10);
        Pins second = Pins.valueOf(10);
        Pins third = Pins.valueOf(10);
        Score strike = Score.strike();

        // when
        Frame finalFrame = FinalFrame.initialize();
        finalFrame.bowl(first);
        finalFrame.bowl(second);
        finalFrame.bowl(third);

        // then
        assertAll(
                () -> assertThat(finalFrame.calculateAdditionalScore(strike)).isNotNull(),
                () -> assertThat(finalFrame.calculateAdditionalScore(strike)).isInstanceOf(Score.class),
                () -> assertThat(finalFrame.calculateAdditionalScore(strike).score()).isEqualTo(30)
        );

    }

}