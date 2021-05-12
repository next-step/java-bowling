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

    @DisplayName("FinalFrame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frame finalFrame = new FinalFrame();

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
        Frame firstFrame = new FinalFrame().bowl(0).bowl(9);
        Frame secondFrame = new FinalFrame().bowl(1).bowl(9);
        Frame thirdFrame = new FinalFrame().bowl(1).bowl(9).bowl(10);
        Frame fourthFrame = new FinalFrame().bowl(10).bowl(10).bowl(10);

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
        Frame firstFrame = new FinalFrame().bowl(0).bowl(9);
        Frame secondFrame = new FinalFrame().bowl(9).bowl(1).bowl(10);

        // then
        assertAll(
                () -> assertThatThrownBy(() -> firstFrame.bowl(10))
                        .isInstanceOf(NoActionBowlException.class)
                        .hasMessage("현재 상태에서는 더 이상 볼을 던질 수 없습니다."),

                () -> assertThatThrownBy(() -> secondFrame.bowl(10))
                        .isInstanceOf(NoActionBowlException.class)
                        .hasMessage("현재 상태에서는 더 이상 볼을 던질 수 없습니다.")
        );

    }

    @DisplayName("FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score() {
        // when
        Frame firstFrame = new FinalFrame().bowl(9).bowl(0);
        Frame secondFrame = new FinalFrame().bowl(9).bowl(1).bowl(9);
        Frame thirdFrame = new FinalFrame().bowl(9).bowl(1).bowl(10);
        Frame fourthFrame = new FinalFrame().bowl(10).bowl(9).bowl(0);
        Frame fifthFrame = new FinalFrame().bowl(10).bowl(9).bowl(1);
        Frame sixthFrame = new FinalFrame().bowl(10).bowl(10).bowl(9);
        Frame seventhFrame = new FinalFrame().bowl(10).bowl(10).bowl(10);

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
        Score miss = Score.miss(Pins.valueOf(9));

        // when
        Frame firstFrame = new FinalFrame().bowl(0).bowl(0);
        Frame secondFrame = new FinalFrame().bowl(1).bowl(8);
        Frame thirdFrame = new FinalFrame().bowl(1).bowl(9).bowl(10);
        Frame fourthFrame = new FinalFrame().bowl(9).bowl(1).bowl(10);
        Frame sixthFrame = new FinalFrame().bowl(10).bowl(10).bowl(10);

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
        Frame firstFrame = new FinalFrame().bowl(0).bowl(0);
        Frame secondFrame = new FinalFrame().bowl(1).bowl(8);
        Frame thirdFrame = new FinalFrame().bowl(1).bowl(9).bowl(10);
        Frame fourthFrame = new FinalFrame().bowl(9).bowl(1).bowl(10);
        Frame sixthFrame = new FinalFrame().bowl(10).bowl(10).bowl(10);

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
        Frame firstFrame = new FinalFrame().bowl(0).bowl(0);
        Frame secondFrame = new FinalFrame().bowl(1).bowl(8);
        Frame thirdFrame = new FinalFrame().bowl(1).bowl(9).bowl(10);
        Frame fourthFrame = new FinalFrame().bowl(9).bowl(1).bowl(10);
        Frame sixthFrame = new FinalFrame().bowl(10).bowl(10).bowl(10);

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
        Frame firstFrame = new FinalFrame().bowl(0);
        Frame secondFrame = new FinalFrame().bowl(9);
        Frame thirdFrame = new FinalFrame().bowl(10);
        Frame fourthFrame = new FinalFrame().bowl(1).bowl(8);
        Frame fifthFrame = new FinalFrame().bowl(0).bowl(9);
        Frame sixthFrame = new FinalFrame().bowl(9).bowl(0);
        Frame seventhFrame = new FinalFrame().bowl(1).bowl(9).bowl(10);
        Frame eightFrame = new FinalFrame().bowl(0).bowl(10).bowl(10);
        Frame nineFrame = new FinalFrame().bowl(10).bowl(0).bowl(10);
        Frame tenFrame = new FinalFrame().bowl(10).bowl(10).bowl(0);
        Frame elevenFrame = new FinalFrame().bowl(10).bowl(10).bowl(10);

        // then
        assertAll(
                () -> assertThat(new FinalFrame().description()).isEqualTo(Strings.EMPTY),
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