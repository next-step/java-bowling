package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.exception.NoMoreBowlException;
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
                        .isInstanceOf(NoMoreBowlException.class)
                        .hasMessage("현재 상태에서는 더 이상 볼을 던질 수 없습니다."),

                () -> assertThatThrownBy(() -> secondFrame.bowl(10))
                        .isInstanceOf(NoMoreBowlException.class)
                        .hasMessage("현재 상태에서는 더 이상 볼을 던질 수 없습니다.")
        );

    }

    @DisplayName("FinalFrame 인스턴스가 알맞는 Score 반환 기능 테스트")
    @Test
    void 반환_score() {
        // given
        Frame firstFrame = new FinalFrame().bowl(9).bowl(0);
        Frame secondFrame = new FinalFrame().bowl(9).bowl(1).bowl(9);
        Frame thirdFrame = new FinalFrame().bowl(9).bowl(1).bowl(10);
        Frame fourthFrame = new FinalFrame().bowl(10).bowl(9).bowl(0);
        Frame fifthFrame = new FinalFrame().bowl(10).bowl(9).bowl(1);
        Frame sixthFrame = new FinalFrame().bowl(10).bowl(10).bowl(9);
        Frame seventhFrame = new FinalFrame().bowl(10).bowl(10).bowl(10);

        // when
        Score firstScore = firstFrame.score();
        Score secondScore = secondFrame.score();
        Score thirdScore = thirdFrame.score();
        Score fourthScore = fourthFrame.score();
        Score fifthScore = fifthFrame.score();
        Score sixthScore = sixthFrame.score();
        Score seventhScore = seventhFrame.score();

        // then
        assertAll(
                () -> assertThat(firstScore.score()).isEqualTo(9),
                () -> assertThat(secondScore.score()).isEqualTo(19),
                () -> assertThat(thirdScore.score()).isEqualTo(20),
                () -> assertThat(fourthScore.score()).isEqualTo(19),
                () -> assertThat(fifthScore.score()).isEqualTo(20),
                () -> assertThat(sixthScore.score()).isEqualTo(29),
                () -> assertThat(seventhScore.score()).isEqualTo(30)
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

        // when
        Score firstScore = firstFrame.calculateAdditionalScore(miss);
        Score secondScore = secondFrame.calculateAdditionalScore(miss);
        Score thirdScore = thirdFrame.calculateAdditionalScore(miss);
        Score fourthScore = fourthFrame.calculateAdditionalScore(miss);
        Score fifthScore = sixthFrame.calculateAdditionalScore(miss);

        // then
        assertAll(
                () -> assertThat(firstScore.score()).isEqualTo(9),
                () -> assertThat(secondScore.score()).isEqualTo(9),
                () -> assertThat(thirdScore.score()).isEqualTo(9),
                () -> assertThat(fourthScore.score()).isEqualTo(9),
                () -> assertThat(fifthScore.score()).isEqualTo(9)
        );
    }

    @DisplayName("beforeScore가 spare였을때, calculateAdditionalScore 계산")
    @Test
    void 반환_calculateAdditionalScore_이전이_spare일때() {
        // given
        Score spare = Score.spare();
        Frame firstFrame = new FinalFrame().bowl(0).bowl(0);
        Frame secondFrame = new FinalFrame().bowl(1).bowl(8);
        Frame thirdFrame = new FinalFrame().bowl(1).bowl(9).bowl(10);
        Frame fourthFrame = new FinalFrame().bowl(9).bowl(1).bowl(10);
        Frame sixthFrame = new FinalFrame().bowl(10).bowl(10).bowl(10);

        // when
        Score firstScore = firstFrame.calculateAdditionalScore(spare);
        Score secondScore = secondFrame.calculateAdditionalScore(spare);
        Score thirdScore = thirdFrame.calculateAdditionalScore(spare);
        Score fourthScore = fourthFrame.calculateAdditionalScore(spare);
        Score fifthScore = sixthFrame.calculateAdditionalScore(spare);

        // then
        assertAll(
                () -> assertThat(firstScore.score()).isEqualTo(10),
                () -> assertThat(secondScore.score()).isEqualTo(11),
                () -> assertThat(thirdScore.score()).isEqualTo(11),
                () -> assertThat(fourthScore.score()).isEqualTo(19),
                () -> assertThat(fifthScore.score()).isEqualTo(20)
        );
    }

    @DisplayName("beforeScore가 strike였을때, calculateAdditionalScore 계산")
    @Test
    void 반환_calculateAdditionalScore_이전이_strike일때() {
        // given
        Score strike = Score.strike();
        Frame firstFrame = new FinalFrame().bowl(0).bowl(0);
        Frame secondFrame = new FinalFrame().bowl(1).bowl(8);
        Frame thirdFrame = new FinalFrame().bowl(1).bowl(9).bowl(10);
        Frame fourthFrame = new FinalFrame().bowl(9).bowl(1).bowl(10);
        Frame sixthFrame = new FinalFrame().bowl(10).bowl(10).bowl(10);

        // when
        Score firstScore = firstFrame.calculateAdditionalScore(strike);
        Score secondScore = secondFrame.calculateAdditionalScore(strike);
        Score thirdScore = thirdFrame.calculateAdditionalScore(strike);
        Score fourthScore = fourthFrame.calculateAdditionalScore(strike);
        Score fifthScore = sixthFrame.calculateAdditionalScore(strike);


        // then
        assertAll(
                () -> assertThat(firstScore.score()).isEqualTo(10),
                () -> assertThat(secondScore.score()).isEqualTo(19),
                () -> assertThat(thirdScore.score()).isEqualTo(20),
                () -> assertThat(fourthScore.score()).isEqualTo(20),
                () -> assertThat(fifthScore.score()).isEqualTo(30)
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