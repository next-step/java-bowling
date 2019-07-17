package bowling.model.frame;

import bowling.model.DownPin;
import bowling.model.frame.state.AbstractStateTest;
import bowling.model.frame.state.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.model.Count.ONCE;
import static bowling.model.Count.ZERO;
import static bowling.model.DownPin.MAX;
import static bowling.model.DownPin.MIN;
import static bowling.model.frame.state.Score.DEFAULT_SCORE;
import static org.assertj.core.api.Assertions.assertThat;

class FrameResultTest extends AbstractStateTest {

    @DisplayName("프레임이 끝나지 않았을 시 점수 계산하지 않는다(히트)")
    @Test
    void getState_hit() {
        // given
        DownPin downPin = DownPin.valueOf(MAX - 1);
        State state = ofHit(downPin);

        // when
        FrameResult frameResult = FrameResult.of(Score.of(downPin), state);
        String expectedState = String.valueOf(downPin.count());

        // then
        assertThat(frameResult).extracting(FrameResult::getScore).isEqualTo(DEFAULT_SCORE);
        assertThat(frameResult).extracting(FrameResult::getState).isEqualTo(expectedState);
    }

    @DisplayName("프레임이 끝나지 않았을 시 점수 계산하지 않는다(거터)")
    @Test
    void getState_gutter() {
        // given
        DownPin downPin = DownPin.valueOf(MIN);
        State state = ofGutter();

        // when
        FrameResult frameResult = FrameResult.of(Score.of(downPin), state);

        // then
        assertThat(frameResult).extracting(FrameResult::getScore).isEqualTo(DEFAULT_SCORE);
        assertThat(frameResult).extracting(FrameResult::getState).isEqualTo(state.printResult());
    }

    @DisplayName("계산이 끝났을 시 결과값을 반환하는데 성공한다")
    @Test
    void getScore_isFinishedAndCalculateComplete_success() {
        // given
        int totalScore = 14;
        Score score = scoreOf(ZERO, totalScore);
        State state = ofStrike();

        // when
        FrameResult frameResult = FrameResult.of(score, state);

        // then
        assertThat(frameResult).extracting(FrameResult::getScore).isEqualTo(totalScore);
    }

    @DisplayName("계산을 끝내지 못 했을 시 default 점수 반환")
    @Test
    void getScore_isFinishedAndCalculateComplete_success2() {
        // given
        Score score = scoreOf(ONCE, 10);
        State state = ofStrike();

        // when
        FrameResult frameResult = FrameResult.of(score, state);

        // then
        assertThat(frameResult.getScore()).isEqualTo(DEFAULT_SCORE);
    }
}