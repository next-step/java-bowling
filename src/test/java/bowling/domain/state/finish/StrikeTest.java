package bowling.domain.state.finish;

import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;
import bowling.exception.ImpossiblePitchException;
import bowling.domain.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class StrikeTest {

    private final State strike = Strike.create();

    @Test
    @DisplayName("생성을 확인한다")
    void create() {
        assertThat(strike).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("심볼을 확인한다")
    void getSymbol() {
        assertThat(strike.getSymbol()).isEqualTo("X");
    }

    @Test
    @DisplayName("프레임을 종료한 상태이다")
    void isFrameStateEnd() {
        assertThat(strike.isFrameEnd()).isTrue();
    }

    @Test
    @DisplayName("투구를 할 수 없다")
    void doNotPitch() {
        assertThatThrownBy(() -> strike.pitch(Pins.create(3))).isInstanceOf(ImpossiblePitchException.class);
    }

    @Test
    @DisplayName("스코어 반환을 확인한다")
    void score() {
        //given
        Score score = strike.score();

        //when,then
        assertAll(
                () -> assertThat(score.finishCalculation()).isFalse(),
                () -> assertThat(score.score()).isEqualTo(10)
        );
    }

    @Test
    @DisplayName("이전 결과가 Miss 이면 이전 스코어를 반환한다")
    void calculateScoreWhenBeforeStateIsMiss() {
        //given
        Score beforeScore = Score.miss(Pins.create(1));
        int expectedScore = beforeScore.score();

        //when
        Score score = strike.calculateScore(beforeScore);

        //then
        assertAll(
                () -> assertThat(score.finishCalculation()).isTrue(),
                () -> assertThat(score.score()).isEqualTo(expectedScore)
        );
    }

    @Test
    @DisplayName("이전 결과가 Spare 이면 이전 스코어와 합산을 확인한다")
    void calculateScoreWhenBeforeStateIsSpare() {
        //given
        Score beforeScore = Score.spare();
        int expectedScore = beforeScore.score() + strike.score().score();

        //when
        Score score = strike.calculateScore(beforeScore);

        //then
        assertAll(
                () -> assertThat(score.finishCalculation()).isTrue(),
                () -> assertThat(score.score()).isEqualTo(expectedScore)
        );
    }

    @Test
    @DisplayName("이전 결과가 Strike 이면 이전 스코어와 합산을 확인한다")
    void calculateScoreWhenBeforeStateIsStrike() {
        //given
        Score beforeScore = Score.strike();
        int expectedScore = beforeScore.score() + strike.score().score();

        //when
        Score score = strike.calculateScore(beforeScore);

        //then
        assertAll(
                () -> assertThat(score.finishCalculation()).isFalse(),
                () -> assertThat(score.score()).isEqualTo(expectedScore)
        );
    }

}
