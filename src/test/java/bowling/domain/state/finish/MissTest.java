package bowling.domain.state.finish;

import bowling.domain.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.exception.ImpossiblePitchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MissTest {

    private final State miss = Miss.of(Pins.create(5), Pins.create(4));

    @Test
    @DisplayName("생성을 확인한다")
    void create() {
        assertThat(miss).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("심볼은 두 투구의 숫자를 반환한다")
    void getSymbol() {
        assertThat(miss.getSymbol()).isEqualTo("5|4");
    }

    @Test
    @DisplayName("프레임을 종료한 상태이다")
    void name() {
        assertThat(miss.isFrameEnd()).isTrue();
    }

    @Test
    @DisplayName("더이상 투구를 할 수 없다")
    void doNotPitch() {
        assertThatThrownBy(() -> miss.pitch(Pins.create(3))).isInstanceOf(ImpossiblePitchException.class);
    }

    @Test
    @DisplayName("거터를 포함할 경우, (-) 심볼을 추가한다")
    void getSymbolIncludedGutter() {
        //given, when
        Pins firstPins = Pins.create(0);
        Pins secondPins = Pins.create(4);
        State missIncludedGutter = Miss.of(firstPins, secondPins);

        //then
        assertThat(missIncludedGutter.getSymbol()).isEqualTo("-|4");
    }

    @Test
    @DisplayName("이전 결과가 Miss 이면 이전 스코어를 반환한다")
    void calculateScoreWhenBeforeStateIsMiss() {
        //given
        Score beforeScore = Score.miss(Pins.create(1));
        int expectedScore = beforeScore.score();

        //when
        Score score = miss.calculateScore(beforeScore);

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
        int expectedScore = beforeScore.score() + Pins.create(5).count();

        //when
        Score score = miss.calculateScore(beforeScore);

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
        int expectedScore = beforeScore.score() + miss.score().score();

        //when
        Score score = miss.calculateScore(beforeScore);

        //then
        assertAll(
                () -> assertThat(score.finishCalculation()).isTrue(),
                () -> assertThat(score.score()).isEqualTo(expectedScore)
        );
    }

}