package bowling.domain.frame;

import bowling.domain.dto.StateDatum;
import bowling.domain.pin.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;
import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LastFrameTest {

    @DisplayName("LastFrame은 state와 함께 생성된다.")
    @Test
    void should_make_last_frame() {
        //arrange, act, assert
        assertThat(LastFrame.of()).isInstanceOf(LastFrame.class);
    }

    @DisplayName("Bowling 게임이 끝났는지 확인 결과를 반환한다")
    @Test
    public void should_return_bowling_game_state() throws Exception {
        //arrange
        LastFrame lastFrame = LastFrame.of();
        lastFrame.hitPins(Pins.of(1));
        lastFrame.hitPins(Pins.of(2));

        //act
        boolean isFinish = lastFrame.isBowlingFinish();

        //assert
        assertTrue(isFinish);
    }

    @DisplayName("LastFrame은 add Frame이 불가능하다")
    @Test
    void should_return_false_when_is_finish() {
        //arrange
        List<Frame> frames = new ArrayList<>();
        frames.add(CommonFrame.of());
        LastFrame lastFrame = LastFrame.of();
        lastFrame.hitPins(Pins.of(10));

        //act
        lastFrame.addFrame(frames);

        //assert
        assertThat(frames.size()).isEqualTo(1);
    }


    @DisplayName("hitPins에 따라 state가 바뀐다")
    @Test
    void should_change_state_hit_pins() {
        //arrange
        Pins pins = Pins.of(3);
        LastFrame lastFrame = LastFrame.of();

        //act
        lastFrame.hitPins(pins);
        lastFrame.hitPins(pins);

        //assert
        assertThat(lastFrame.getFrameStates().getStateData().get(0).getType()).isEqualTo(Miss.class.getSimpleName());
    }

    @DisplayName("시도 카운트가 limit에 도달하면 isBowlingFinish가 true를 반환한다")
    @Test
    void should_return_finish_true_when_attempt_count_limit() {
        //arrange
        LastFrame lastFrame = new TestingLastFrame(Start.of());

        //act, assert
        assertTrue(lastFrame.isBowlingFinish());
    }

    @DisplayName("LastState가 miss이면 isBowlingFinish가 true를 반환한다")
    @Test
    void should_return_finish_true_when_last_state_is_miss() {
        //arrange
        LastFrame lastFrame = new TestingLastFrame(Start.of());

        //act, assert
        assertTrue(lastFrame.isBowlingFinish());
    }

    @DisplayName("isFinish가 true이면 start도 true를 반환한다")
    @Test
    void should_return_is_start_true_when_is_finish_true() {
        //arrange
        LastFrame lastFrame = new TestingLastFrame(Start.of());

        //act, assert
        assertTrue(lastFrame.isStart());
    }

    @DisplayName("state의 start가 true이면 start도 true를 반환한다")
    @Test
    void should_return_is_start_true_when_state_is_true() {
        //arrange
        LastFrame lastFrame = LastFrame.of();

        //act, assert
        assertTrue(lastFrame.isStart());
    }

    @DisplayName("state의 start가 false이면 start도 true를 반환한다")
    @Test
    void should_return_is_start_true_when_state_is_false() {
        //arrange
        LastFrame lastFrame = LastFrame.of();

        //act
        lastFrame.hitPins(Pins.of(3));

        //assert
        assertFalse(lastFrame.isStart());
    }

    @DisplayName("LastFrame의 여러 상태의 점수 계산 테스트")
    @MethodSource
    @ParameterizedTest
    void should_add_score(List<Pins> pins, Score expectedScore) {
        //arrange
        LastFrame lastFrame = LastFrame.of();
        pins.forEach(lastFrame::hitPins);

        //act, assert
        assertThat(lastFrame.getScore()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> should_add_score() {
        return Stream.of(
                Arguments.of(
                        Collections.emptyList(),
                        ComputableScore.ofBase()),

                Arguments.of(
                        Collections.singletonList(Pins.of(10)),
                        ProgressScore.ofStrike()),

                Arguments.of(
                        Collections.singletonList(Pins.of(1)),
                        ComputableScore.ofBase()),

                Arguments.of(
                        Arrays.asList(Pins.of(1), Pins.of(9)),
                        ProgressScore.ofSpare()),

                Arguments.of(
                        Arrays.asList(Pins.of(1), Pins.of(2)),
                        ComputableScore.of(3)
                )
        );
    }

    private static class TestingLastFrame extends LastFrame {

        protected TestingLastFrame(State state) {
            super(state);
        }

        @Override
        protected boolean isLimitAttemptCount() {
            return true;
        }

        @Override
        protected boolean isLastStateMiss() {
            return true;
        }
    }


}