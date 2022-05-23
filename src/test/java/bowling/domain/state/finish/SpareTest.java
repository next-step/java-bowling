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

class SpareTest {

    private final State spare = Spare.create(Pins.create(5));

    @Test
    @DisplayName(" 생성을 확인한다")
    void create() {
        assertThat(spare).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("프레임을 종료한 상태이다")
    void isFrameEnd() {
        assertThat(spare.isFrameEnd()).isTrue();
    }

    @Test
    @DisplayName("심볼은 첫번째 투구와 (/) 심볼을 반환한다")
    void getSymbol() {
        assertThat(spare.getSymbol()).isEqualTo("5|/");
    }

    @Test
    @DisplayName("투구할 수 없다")
    void doNotPitch() {
        assertThatThrownBy(() -> spare.pitch(Pins.create(3))).isInstanceOf(ImpossiblePitchException.class);
    }

    @Test
    @DisplayName("이전 결과가 Miss 이면 이전 스코어를 반환한다")
    void calculateScoreWhenBeforeStateIsMiss() {
        Score beforeScore = Score.miss(Pins.create(1));
        int expectedScore = beforeScore.score();

        Score score = spare.calculateScore(beforeScore);

        assertAll(
                () -> assertThat(score.finishCalculation()).isTrue(),
                () -> assertThat(score.score()).isEqualTo(expectedScore)
        );
    }

    @Test
    @DisplayName("이전 결과가 Spare 이면 이전 스코어와 합산을 확인한다")
    void calculateScoreWhenBeforeStateIsSpare() {
        Score beforeScore = Score.spare();
        int expectedScore = beforeScore.score() + Pins.create(5).count();

        Score score = spare.calculateScore(beforeScore);

        assertAll(
                () -> assertThat(score.finishCalculation()).isTrue(),
                () -> assertThat(score.score()).isEqualTo(expectedScore)
        );
    }

    @Test
    @DisplayName("이전 결과가 Strike 이면 이전 스코어와 합산을 확인한다")
    void calculateScoreWhenBeforeStateIsStrike() {
        Score beforeScore = Score.strike();
        int expectedScore = beforeScore.score() + spare.score().score();

        Score score = spare.calculateScore(beforeScore);

        assertAll(
                () -> assertThat(score.finishCalculation()).isTrue(),
                () -> assertThat(score.score()).isEqualTo(expectedScore)
        );
    }

}