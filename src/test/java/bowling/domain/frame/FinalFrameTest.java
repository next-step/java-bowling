package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.exception.NoActionBowlException;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    private static final Pins ZERO = Pins.valueOf(0);
    private static final Pins ONE = Pins.valueOf(1);
    private static final Pins EIGHT = Pins.valueOf(8);
    private static final Pins NINE = Pins.valueOf(9);
    private static final Pins TEN = Pins.valueOf(10);

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

    @DisplayName("FinalFrame 의 bowl 실행 및 종료 여부 테스트")
    @Test
    void 투구_bowl() {
        // when
        Frame firstFrame = FinalFrame.initialize().bowl(ZERO).bowl(NINE);
        Frame secondFrame = FinalFrame.initialize().bowl(ONE).bowl(NINE);
        Frame thirdFrame = FinalFrame.initialize().bowl(ONE).bowl(NINE).bowl(TEN);
        Frame fourthFrame = FinalFrame.initialize().bowl(TEN).bowl(TEN).bowl(TEN);

        // then
        assertAll(
                () -> assertThat(firstFrame.isFinish()).isTrue(),
                () -> assertThat(secondFrame.isFinish()).isFalse(),
                () -> assertThat(thirdFrame.isFinish()).isTrue(),
                () -> assertThat(fourthFrame.isFinish()).isTrue()
        );
    }

    @DisplayName("FinalFrame bowl() 예외처리 테스트")
    @Test
    void bowl_보너스_있을때_예외처리() {
        // when
        Frame firstFrame = FinalFrame.initialize().bowl(ZERO).bowl(NINE);
        Frame secondFrame = FinalFrame.initialize().bowl(NINE).bowl(ONE).bowl(TEN);

        // then
        assertAll(
                () -> assertThatThrownBy(() -> firstFrame.bowl(TEN))
                        .isInstanceOf(NoActionBowlException.class)
                        .hasMessage("현재 상태에서는 더 이상 볼을 던질 수 없습니다."),

                () -> assertThatThrownBy(() -> secondFrame.bowl(TEN))
                        .isInstanceOf(NoActionBowlException.class)
                        .hasMessage("현재 상태에서는 더 이상 볼을 던질 수 없습니다.")
        );

    }

    @DisplayName("FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score() {
        // when
        Frame firstFrame = FinalFrame.initialize().bowl(NINE).bowl(ZERO);
        Frame secondFrame = FinalFrame.initialize().bowl(NINE).bowl(ONE).bowl(NINE);
        Frame thirdFrame = FinalFrame.initialize().bowl(NINE).bowl(ONE).bowl(TEN);
        Frame fourthFrame = FinalFrame.initialize().bowl(TEN).bowl(NINE).bowl(ZERO);
        Frame fifthFrame = FinalFrame.initialize().bowl(TEN).bowl(NINE).bowl(ONE);
        Frame sixthFrame = FinalFrame.initialize().bowl(TEN).bowl(TEN).bowl(NINE);
        Frame seventhFrame = FinalFrame.initialize().bowl(TEN).bowl(TEN).bowl(TEN);

        // then
        assertAll(
                () -> assertThat(firstFrame.score().score()).isEqualTo(9),
                () -> assertThat(secondFrame.score().score()).isEqualTo(19),
                () -> assertThat(thirdFrame.score().score()).isEqualTo(20),
                () -> assertThat(fourthFrame.score().score()).isEqualTo(19),
                () -> assertThat(fifthFrame.score().score()).isEqualTo(20),
                () -> assertThat(sixthFrame.score().score()).isEqualTo(29),
                () -> assertThat(seventhFrame.score().score()).isEqualTo(30)
        );

    }

    @DisplayName("beforeScore가 miss였을때, calculateAdditionalScore 계산")
    @Test
    void 반환_calculateAdditionalScore_이전이_miss일때() {
        // given
        Score miss = Score.miss(NINE);

        // when
        Frame firstFrame = FinalFrame.initialize().bowl(ZERO).bowl(ZERO);
        Frame secondFrame = FinalFrame.initialize().bowl(ONE).bowl(EIGHT);
        Frame thirdFrame = FinalFrame.initialize().bowl(ONE).bowl(NINE).bowl(TEN);
        Frame fourthFrame = FinalFrame.initialize().bowl(NINE).bowl(ONE).bowl(TEN);
        Frame sixthFrame = FinalFrame.initialize().bowl(TEN).bowl(TEN).bowl(TEN);

        // then
        assertAll(
                () -> assertThat(firstFrame.calculateAdditionalScore(miss).score()).isEqualTo(9),
                () -> assertThat(secondFrame.calculateAdditionalScore(miss).score()).isEqualTo(9),
                () -> assertThat(thirdFrame.calculateAdditionalScore(miss).score()).isEqualTo(9),
                () -> assertThat(fourthFrame.calculateAdditionalScore(miss).score()).isEqualTo(9),
                () -> assertThat(sixthFrame.calculateAdditionalScore(miss).score()).isEqualTo(9)
        );
    }

    @DisplayName("beforeScore가 spare였을때, calculateAdditionalScore 계산")
    @Test
    void 반환_calculateAdditionalScore_이전이_spare일때() {
        // given
        Score spare = Score.spare();

        // when
        Frame firstFrame = FinalFrame.initialize().bowl(ZERO).bowl(ZERO);
        Frame secondFrame = FinalFrame.initialize().bowl(ONE).bowl(EIGHT);
        Frame thirdFrame = FinalFrame.initialize().bowl(ONE).bowl(NINE).bowl(TEN);
        Frame fourthFrame = FinalFrame.initialize().bowl(NINE).bowl(ONE).bowl(TEN);
        Frame sixthFrame = FinalFrame.initialize().bowl(TEN).bowl(TEN).bowl(TEN);

        // then
        assertAll(
                () -> assertThat(firstFrame.calculateAdditionalScore(spare).score()).isEqualTo(10),
                () -> assertThat(secondFrame.calculateAdditionalScore(spare).score()).isEqualTo(11),
                () -> assertThat(thirdFrame.calculateAdditionalScore(spare).score()).isEqualTo(11),
                () -> assertThat(fourthFrame.calculateAdditionalScore(spare).score()).isEqualTo(19),
                () -> assertThat(sixthFrame.calculateAdditionalScore(spare).score()).isEqualTo(20)
        );
    }

    @DisplayName("beforeScore가 strike였을때, calculateAdditionalScore 계산")
    @Test
    void 반환_calculateAdditionalScore_이전이_strike일때() {
        // given
        Score strike = Score.strike();

        // when
        Frame firstFrame = FinalFrame.initialize().bowl(ZERO).bowl(ZERO);
        Frame secondFrame = FinalFrame.initialize().bowl(ONE).bowl(EIGHT);
        Frame thirdFrame = FinalFrame.initialize().bowl(ONE).bowl(NINE).bowl(TEN);
        Frame fourthFrame = FinalFrame.initialize().bowl(NINE).bowl(ONE).bowl(TEN);
        Frame sixthFrame = FinalFrame.initialize().bowl(TEN).bowl(TEN).bowl(TEN);

        // then
        assertAll(
                () -> assertThat(firstFrame.calculateAdditionalScore(strike).score()).isEqualTo(10),
                () -> assertThat(secondFrame.calculateAdditionalScore(strike).score()).isEqualTo(19),
                () -> assertThat(thirdFrame.calculateAdditionalScore(strike).score()).isEqualTo(20),
                () -> assertThat(fourthFrame.calculateAdditionalScore(strike).score()).isEqualTo(20),
                () -> assertThat(sixthFrame.calculateAdditionalScore(strike).score()).isEqualTo(30)
        );
    }

    @DisplayName("FinalFrame 의 description 기능 테스트")
    @Test
    void 반환_description() {
        // when
        Frame firstFrame = FinalFrame.initialize().bowl(ZERO);
        Frame secondFrame = FinalFrame.initialize().bowl(NINE);
        Frame thirdFrame = FinalFrame.initialize().bowl(TEN);
        Frame fourthFrame = FinalFrame.initialize().bowl(ONE).bowl(EIGHT);
        Frame fifthFrame = FinalFrame.initialize().bowl(ZERO).bowl(NINE);
        Frame sixthFrame = FinalFrame.initialize().bowl(NINE).bowl(ZERO);
        Frame seventhFrame = FinalFrame.initialize().bowl(ONE).bowl(NINE).bowl(TEN);
        Frame eightFrame = FinalFrame.initialize().bowl(ZERO).bowl(TEN).bowl(TEN);
        Frame nineFrame = FinalFrame.initialize().bowl(TEN).bowl(ZERO).bowl(TEN);
        Frame tenFrame = FinalFrame.initialize().bowl(TEN).bowl(TEN).bowl(ZERO);
        Frame elevenFrame = FinalFrame.initialize().bowl(TEN).bowl(TEN).bowl(TEN);

        // then
        assertAll(
                () -> assertThat(FinalFrame.initialize().description()).isEqualTo(Strings.EMPTY),
                () -> assertThat(firstFrame.description()).isEqualTo("-"),
                () -> assertThat(secondFrame.description()).isEqualTo("9"),
                () -> assertThat(thirdFrame.description()).isEqualTo("X"),
                () -> assertThat(fourthFrame.description()).isEqualTo("1|8"),
                () -> assertThat(fifthFrame.description()).isEqualTo("-|9"),
                () -> assertThat(sixthFrame.description()).isEqualTo("9|-"),
                () -> assertThat(seventhFrame.description()).isEqualTo("1|/|X"),
                () -> assertThat(eightFrame.description()).isEqualTo("-|/|X"),
                () -> assertThat(nineFrame.description()).isEqualTo("X|-|/"),
                () -> assertThat(tenFrame.description()).isEqualTo("X|X|-"),
                () -> assertThat(elevenFrame.description()).isEqualTo("X|X|X")
        );

    }

}