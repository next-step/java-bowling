package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Nested
    @DisplayName("이전 Score를 받아 최종 score점수를 계산할 수 있다.")
    class calculateAdditionalScoreTest {

        private Score score;
        private Score expected;

        @Nested
        @DisplayName("이전 Score의 남은 횟수가 2인 경우 2번 더해진다.")
        class scoreRemainTwoLeft {

            @BeforeEach
            void beforeEach() {
                score = Score.from(10, 2);
            }

            @Test
            @DisplayName("스트라이크 두번이 있으면 20점 추가된다.")
            void twoStrikeTest() {

                // given
                List<State> states = new ArrayList<>();
                states.add(new Strike(Pin.of(10)));
                states.add(new Strike(Pin.of(10)));
                Frame frame = FinalFrame.from(10, states);

                expected = Score.from(30, 0);

                // when
                Score result = frame.calculateAdditionalScore(score);

                // then
                assertThat(result).isEqualTo(expected);
            }

            @Test
            @DisplayName("Spare가 있으면 10점 추가된다.")
            void spareTest() {

                // given
                List<State> states = new ArrayList<>();
                states.add(new Spare(Pin.of(3), Pin.of(7)));
                Frame frame = FinalFrame.from(10, states);

                expected = Score.from(20, 0);

                // when
                Score result = frame.calculateAdditionalScore(score);

                // then
                assertThat(result).isEqualTo(expected);
            }

            @Test
            @DisplayName("Miss라면 남은 점수만큼 추가된다.")
            void missTest() {

                // given
                List<State> states = new ArrayList<>();
                states.add(new Miss(Pin.of(2), Pin.of(4)));
                Frame frame = FinalFrame.from(10, states);

                expected = Score.from(16, 0);

                // when
                Score result = frame.calculateAdditionalScore(score);

                // then
                assertThat(result).isEqualTo(expected);
            }

        }
    }

}