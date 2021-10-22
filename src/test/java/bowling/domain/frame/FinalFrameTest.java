package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.FirstBowl;
import bowling.exception.frame.FinalFrameBowlingException;
import java.util.LinkedList;
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
                LinkedList<State> states = new LinkedList<>();
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
                LinkedList<State> states = new LinkedList<>();
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
                LinkedList<State> states = new LinkedList<>();
                states.add(new Miss(Pin.of(2), Pin.of(4)));
                Frame frame = FinalFrame.from(10, states);

                expected = Score.from(16, 0);

                // when
                Score result = frame.calculateAdditionalScore(score);

                // then
                assertThat(result).isEqualTo(expected);
            }

        }

        @Nested
        @DisplayName("이전 Score의 남은 횟수가 1인 경우 1번 더해진다.")
        class scoreRemainOneLeft {

            @BeforeEach
            void beforeEach() {
                score = Score.from(10, 1);
            }

            @Test
            @DisplayName("스트라이크가 있으면 스트라이크 점수가 추가된다.")
            void strikeTest() {

                // given
                LinkedList<State> states = new LinkedList<>();
                states.add(new Strike(Pin.of(10)));
                states.add(new Strike(Pin.of(10)));
                Frame frame = FinalFrame.from(10, states);

                expected = Score.from(20, 0);

                // when
                Score result = frame.calculateAdditionalScore(score);

                // then
                assertThat(result).isEqualTo(expected);
            }

            @Test
            @DisplayName("스페어가 있으면 스페어 첫 핀 점수가 추가된다.")
            void spareTest() {

                // given
                LinkedList<State> states = new LinkedList<>();
                states.add(new Spare(Pin.of(3), Pin.of(7)));
                Frame frame = FinalFrame.from(10, states);

                expected = Score.from(13, 0);

                // when
                Score result = frame.calculateAdditionalScore(score);

                // then
                assertThat(result).isEqualTo(expected);
            }

            @Test
            @DisplayName("Miss가 있으면 miss 첫 핀 점수가 추가된다.")
            void missTest() {

                // given
                LinkedList<State> states = new LinkedList<>();
                states.add(new Miss(Pin.of(2), Pin.of(4)));
                Frame frame = FinalFrame.from(10, states);

                expected = Score.from(12, 0);

                // when
                Score result = frame.calculateAdditionalScore(score);

                // then
                assertThat(result).isEqualTo(expected);
            }

            @Test
            @DisplayName("FirstBowl이 있으면 firstBowl 첫 핀 점수가 추가된다.")
            void firstBowlTest() {

                // given
                LinkedList<State> states = new LinkedList<>();
                states.add(new FirstBowl(Pin.of(2)));
                Frame frame = FinalFrame.from(10, states);

                expected = Score.from(12, 0);

                // when
                Score result = frame.calculateAdditionalScore(score);

                // then
                assertThat(result).isEqualTo(expected);
            }
        }
    }

    @Nested
    @DisplayName("FinalFrame의 score를 계산할 수 있다.")
    class getScoreTest {

        private Score expected;

        @Test
        @DisplayName("strike - strike - strike : 30")
        void strikeThreeTest() {

            // given
            LinkedList<State> states = new LinkedList<>();
            states.add(new Strike(Pin.of(10)));
            states.add(new Strike(Pin.of(10)));
            states.add(new Strike(Pin.of(10)));
            FinalFrame frame = (FinalFrame) FinalFrame.from(10, states);

            expected = Score.from(30, 0);

            // when
            Score result = frame.score();

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("strike - strike - firstBowl : 20 + firstBowl pin")
        void strikeTwoFirstBowlTest() {

            // given
            LinkedList<State> states = new LinkedList<>();
            states.add(new Strike(Pin.of(10)));
            states.add(new Strike(Pin.of(10)));
            states.add(new FirstBowl(Pin.of(3)));
            FinalFrame frame = (FinalFrame) FinalFrame.from(10, states);

            expected = Score.from(23, 0);

            // when
            Score result = frame.score();

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("strike - spare : 20")
        void strikeSpareTest() {

            // given
            LinkedList<State> states = new LinkedList<>();
            states.add(new Strike(Pin.of(10)));
            states.add(new Spare(Pin.of(3), Pin.of(7)));
            FinalFrame frame = (FinalFrame) FinalFrame.from(10, states);

            expected = Score.from(20, 0);

            // when
            Score result = frame.score();

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("strike - miss : 10 + miss score")
        void strikeMissTest() {

            // given
            LinkedList<State> states = new LinkedList<>();
            states.add(new Strike(Pin.of(10)));
            states.add(new Miss(Pin.of(2), Pin.of(4)));
            FinalFrame frame = (FinalFrame) FinalFrame.from(10, states);

            expected = Score.from(16, 0);

            // when
            Score result = frame.score();

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("spare - strike : 20")
        void spareStrikeTest() {

            // given
            LinkedList<State> states = new LinkedList<>();
            states.add(new Spare(Pin.of(3), Pin.of(7)));
            states.add(new Strike(Pin.of(10)));
            FinalFrame frame = (FinalFrame) FinalFrame.from(10, states);

            expected = Score.from(20, 0);

            // when
            Score result = frame.score();

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("spare - firstBowl : 10 + firstBowl pin")
        void spareFirstBowlTest() {

            // given
            LinkedList<State> states = new LinkedList<>();
            states.add(new Spare(Pin.of(3), Pin.of(7)));
            states.add(new FirstBowl(Pin.of(3)));
            FinalFrame frame = (FinalFrame) FinalFrame.from(10, states);

            expected = Score.from(13, 0);

            // when
            Score result = frame.score();

            // then
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("miss : miss score")
        void missTest() {

            // given
            LinkedList<State> states = new LinkedList<>();
            states.add(new Miss(Pin.of(2), Pin.of(4)));
            FinalFrame frame = (FinalFrame) FinalFrame.from(10, states);

            expected = Score.from(6, 0);

            // when
            Score result = frame.score();

            // then
            assertThat(result).isEqualTo(expected);
        }
    }

    @Test
    @DisplayName("현재 bowling을 할 bonus 횟수가 남아있지 않다면 exception이 발생해야 한다.")
    void bowlingExceptionTest() {

        // given
        LinkedList<State> states = new LinkedList<>();
        states.add(new Spare(Pin.of(3), Pin.of(7)));
        states.add(new Strike(Pin.of(10)));
        Frame frame = FinalFrame.from(10, states);

        // when & then
        assertThatExceptionOfType(FinalFrameBowlingException.class)
            .isThrownBy(() -> frame.bowling(Pin.of(3)))
            .withMessageMatching("final frame이 종료되어 더이상 bowling할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 bolwing 횟수가 남아있을 경우 bowling을 진행할 수 있다.")
    void bowlingTest() {

        // given
        LinkedList<State> states = new LinkedList<>();
        states.add(new Strike(Pin.of(10)));
        FinalFrame frame = (FinalFrame) FinalFrame.from(10, states);

        // when
        FinalFrame firstBowl = (FinalFrame) frame.bowling(Pin.of(10));
        FinalFrame result = (FinalFrame) firstBowl.bowling(Pin.of(10));

        // then
        assertAll(
            () -> assertThat(result).isInstanceOf(FinalFrame.class),
            () -> assertThat(result).isInstanceOf(Frame.class),
            () -> assertThat(result.score()).isEqualTo(Score.from(30, 0))
        );
    }

    @Test
    @DisplayName("완료된 score를 가진 Frame으로 FrameResult를 생성할 수 있다.")
    void createFrameResultTest() {

        // given
        LinkedList<State> states = new LinkedList<>();
        states.add(new Strike(Pin.of(10)));
        states.add(new Strike(Pin.of(10)));
        states.add(new Strike(Pin.of(10)));
        Frame frame = FinalFrame.from(10, states);

        FrameResult expected = FrameResult.of(30);

        // when
        FrameResult result = frame.createFrameResult();

        // then
        assertThat(result).isEqualTo(expected);
    }

}