package bowling.domain.state.running;

import bowling.domain.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.exception.InvalidPitchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class FirstBowlTest {

    private final State firstBowl = FirstBowl.create(Pins.create(5));

    @Test
    @DisplayName("첫 번째 볼을 생성한다")
    void create() {
        assertThat(firstBowl).isInstanceOf(FirstBowl.class);
    }

    @Test
    @DisplayName("첫 번째 볼은 프레임을 진행하고 있는 상태이다")
    void isFrameEndFalse() {
        assertThat(firstBowl.isFrameEnd()).isFalse();
    }

    @Test
    @DisplayName("모든 핀들을 초과할 경우 예외를 반환한다")
    void exceedAllPins() {
        assertThatThrownBy(() -> firstBowl.pitch(Pins.create(6))).isInstanceOf(InvalidPitchException.class);
    }

    @Test
    @DisplayName("첫 번째 볼은 쓰러트린 핀을 반환한다")
    void getSymbol() {
        assertThat(firstBowl.getSymbol()).isEqualTo("5");
    }

    @Test
    @DisplayName("두 시도만에 모든 볼링핀을 쓰러트리면 스페어를 반환한다")
    void spare() {
        assertThat(firstBowl.pitch(Pins.create(5))).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("두 시도만에 모든 볼링핀을 못 쓰러트리면 미스를 반환한다")
    void miss() {
        assertThat(firstBowl.pitch(Pins.create(4))).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("이전 결과가 Miss 이면 이전 스코어를 반환한다")
    void calculateScoreWhenBeforeStateIsMiss() {
        //given
        Score beforeScore = Score.miss(Pins.create(1));
        int expectedScore = beforeScore.score();

        //when
        Score score = firstBowl.calculateScore(beforeScore);

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
        Score score = firstBowl.calculateScore(beforeScore);

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
        int expectedScore = beforeScore.score() + Pins.create(5).count();

        //when
        Score score = firstBowl.calculateScore(beforeScore);

        //then
        assertAll(
                () -> assertThat(score.finishCalculation()).isFalse(),
                () -> assertThat(score.score()).isEqualTo(expectedScore)
        );
    }

}