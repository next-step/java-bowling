package bowling.domain.frame;

import bowling.domain.score.NormalScore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"InnerClassMayBeStatic", "NonAsciiCharacters"})
class NormalFrameTest {

    private static final int NONE = -1;

    @Nested
    @DisplayName("첫 프레임일 때")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 첫_프레임일_때 {

        @Test
        public void 첫번째_NormalFrame을_생성할_수_있다() {
            //given
            //when
            //then
            assertAll(
                    () -> assertEquals(NormalFrame.start(1), NormalFrame.start(1)),
                    () -> assertEquals(NormalFrame.start(10), NormalFrame.of(1, NormalScore.first(10), 1)),
                    () -> assertEquals(NormalFrame.start(0).bowl(3), NormalFrame.of(1, NormalScore.first(0).accumulate(3), 2))
            );
        }

        @Test
        public void 첫시도에서_스트라이크를_치면_Frame이_끝난다() {
            //given
            //when
            Frame frame = NormalFrame.start(10);
            //then
            assertThat(frame.nextIdx()).isEqualTo(2);
        }

        @Test
        public void 첫시도에서_스트라이크를_치지_못하면_Frame이_끝나지_않는다() {
            //given
            //when
            Frame frame = NormalFrame.start(1);
            //then
            assertThat(frame.nextIdx()).isEqualTo(1);
        }

        @Test
        public void 첫번째_시도_후_두번째_시도를_하지_않으면_끝나지_않는다() {
            //given
            //when
            Frame frame = NormalFrame.start(1);
            //then
            assertThat(frame.isLast()).isFalse();
        }

        @Test
        public void 한_프레임의_모든점수를_가져올_수_있다() {
            //given
            Frame frame = NormalFrame.start(1).bowl(3);
            //when
            List<Integer> scores = frame.getAllScores();
            //then
            assertThat(scores).containsExactly(1, 3);
        }

        @Test
        public void 프레임이_스트라이크와_스페어가_아닐때의_프레임의_점수를_계산한다() {
            //given
            Frame frame = NormalFrame.start(1).bowl(3);
            //when
            int score = frame.calculateScore();
            //then
            assertThat(score).isEqualTo(4);
        }

        @Test
        public void 프레임이_스트라이크와_스페어가_아닐때_두번째_시도가_끝나지_않았다면_점수를_계산할_수_없다() {
            //given
            Frame frame = NormalFrame.start(1);
            //when
            int score = frame.calculateScore();
            //then
            assertThat(score).isEqualTo(NONE);
        }

        @Test
        public void 프레임이_스페어이고_다음_프레임의_첫시도가_주어졌을_때_프레임의_점수를_계산한다() {
            //given
            Frame frame = NormalFrame.start(1).bowl(9);
            Frame nextFrame = frame.bowl(10);
            //when
            int score = frame.calculateScore();
            //then
            assertThat(score).isEqualTo(20);
        }

        @Test
        public void 프레임이_스페어이고_다음_프레임의_첫시도가_주어지지_않았다면_점수를_계산할_수_없다() {
            //given
            Frame frame = NormalFrame.start(1).bowl(9);
            //when
            int score = frame.calculateScore();
            //then
            assertThat(score).isEqualTo(NONE);
        }

        @ParameterizedTest
        @MethodSource
        public void 프레임이_스트라이크이고_다음_프레임이_스트라이크가_아닐_때_점수를_계산할_수_있다(int first, int second) {
            //given
            Frame frame = NormalFrame.start(10);
            Frame nextFrame = frame.bowl(first).bowl(second);
            //when
            int score = frame.calculateScore();
            //then
            assertThat(score).isEqualTo(10 + first + second);
        }

        private Stream<Arguments> 프레임이_스트라이크이고_다음_프레임이_스트라이크가_아닐_때_점수를_계산할_수_있다() {
            return Stream.of(
                    Arguments.of(1, 1),
                    Arguments.of(1, 9),
                    Arguments.of(3, 7),
                    Arguments.of(0, 10)
            );
        }

        @Test
        public void 프레임이_스트라이크이고_다음_프레임이_스트라이크가_아닐_때_다음_프레임이_끝나지_않았다면_점수를_계산할_수_없다() {
            //given
            Frame frame = NormalFrame.start(10);
            Frame nextFrame = frame.bowl(3);
            //when
            int score = frame.calculateScore();
            //then
            assertThat(score).isEqualTo(NONE);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 5, 10})
        public void 프레임이_스트라이크이고_다음_프레임이_스트라이크면_그_다음_프레임의_첫_점수를_합한값이_결과가_된다(int first) {
            //given
            Frame frame = NormalFrame.start(10);
            Frame nextFrame = frame.bowl(10);
            Frame nextNextFrame = nextFrame.bowl(first);
            //when
            int score = frame.calculateScore();
            //then
            assertThat(score).isEqualTo(20 + first);
        }

        @Test
        public void 프레임이_스트라이크이고_다음_프레임이_스트라이크이면_그_다음_프레임이_끝나지_않았다면_점수를_계산할_수_없다() {
            //given
            Frame frame = NormalFrame.start(10);
            Frame nextFrame = frame.bowl(10);
            //when
            int score = frame.calculateScore();
            //then
            assertThat(score).isEqualTo(NONE);
        }

    }


    @Nested
    @DisplayName("첫 프레임이 아닐 때")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 첫_프레임이_아닐_때 {

        @Test
        public void _9회차의_첫시도에서_스트라이크를_치면_끝난다() {
            //given
            Frame frame = NormalFrame.of(8, NormalScore.first(3).accumulate(3), 2);
            //when
            frame = frame.bowl(10);
            //then
            Frame finalFrame = frame;
            assertAll(
                    () -> assertEquals(10, finalFrame.nextIdx()),
                    () -> assertTrue(finalFrame.isLast())
            );
        }

        @Test
        public void _9회차의_첫시도가_스트라이크가_아니면_끝나지_않는다() {
            //given
            Frame frame = NormalFrame.of(8, NormalScore.first(3).accumulate(3), 2);
            //when
            frame = frame.bowl(8);
            //then
            Frame finalFrame = frame;
            assertAll(
                    () -> assertEquals(9, finalFrame.nextIdx()),
                    () -> assertFalse(finalFrame.isLast())
            );
        }

        @Test
        public void _9회차의_두번째_시도_후_끝난다() {
            //given
            Frame frame = NormalFrame.of(8, NormalScore.first(3).accumulate(3), 2);
            //when
            frame = frame.bowl(8).bowl(2);
            //then
            Frame finalFrame = frame;
            assertAll(
                    () -> assertEquals(10, finalFrame.nextIdx()),
                    () -> assertTrue(finalFrame.isLast())
            );
        }


        @Test
        public void 이전_프레임의_점수가_계산되어있지_않다면_현프레임의_점수를_계산할_수_없다() {
            //given
            Frame prevFrame = NormalFrame.start(1).bowl(3);
            Frame frame = prevFrame.bowl(4).bowl(3);
            //when
            int score = frame.calculateScore();
            //then
            assertThat(score).isEqualTo(NONE);
        }

        @Nested
        @DisplayName("이전 프레임의 점수가 계산되었고")
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        class 이전_프레임의_점수가_계산되었고 {

            Frame prevFrame;

            @BeforeEach
            public void setup() {
                prevFrame = NormalFrame.start(1).bowl(3);
                prevFrame.calculateScore();
            }

            @Test
            public void 현프레임이_스트라이크와_스페어가_아닐때의_프레임의_점수를_계산한다() {
                //given
                Frame frame = prevFrame.bowl(4).bowl(3);
                //when
                prevFrame.calculateScore();
                int score = frame.calculateScore();
                //then
                assertThat(score).isEqualTo(1 + 3 + 4 + 3);
            }

            @Test
            public void 프레임이_스트라이크와_스페어가_아닐때_두번째_시도가_끝나지_않았다면_점수를_계산할_수_없다() {
                //given
                Frame frame = prevFrame.bowl(4);
                //when
                prevFrame.calculateScore();
                int score = frame.calculateScore();
                //then
                assertThat(score).isEqualTo(NONE);
            }

            @ParameterizedTest
            @MethodSource
            public void 프레임이_스페어이고_다음_프레임의_첫시도가_주어졌을_때_프레임의_점수를_계산한다(int first, int second) {
                //given
                Frame frame = prevFrame.bowl(1).bowl(9);  // 스페어
                Frame nextFrame = frame.bowl(first).bowl(second);
                //when
                prevFrame.calculateScore();
                int score = frame.calculateScore();
                //then
                assertThat(score).isEqualTo(1 + 3 + 1 + 9 + first);
            }

            private Stream<Arguments> 프레임이_스페어이고_다음_프레임의_첫시도가_주어졌을_때_프레임의_점수를_계산한다() {
                return Stream.of(
                        Arguments.of(1, 1),
                        Arguments.of(1, 9),
                        Arguments.of(3, 7),
                        Arguments.of(0, 10),
                        Arguments.of(10, 0)
                );
            }

            @Test
            public void 프레임이_스페어이고_다음_프레임의_첫시도가_주어지지_않았다면_점수를_계산할_수_없다() {
                //given
                Frame frame = prevFrame.bowl(1).bowl(9);  // 스페어
                //when
                prevFrame.calculateScore();
                int score = frame.calculateScore();
                //then
                assertThat(score).isEqualTo(NONE);
            }

            @ParameterizedTest
            @MethodSource
            public void 프레임이_스트라이크이고_다음_프레임이_스트라이크가_아닐_때_점수를_계산할_수_있다(int first, int second) {
                //given
                Frame frame = prevFrame.bowl(10);    // 스트라이크
                Frame nextFrame = frame.bowl(first).bowl(second);
                //when
                prevFrame.calculateScore();
                int score = frame.calculateScore();
                //then
                assertThat(score).isEqualTo(1 + 3 + 10 + first + second);
            }

            private Stream<Arguments> 프레임이_스트라이크이고_다음_프레임이_스트라이크가_아닐_때_점수를_계산할_수_있다() {
                return Stream.of(
                        Arguments.of(1, 1),
                        Arguments.of(1, 9),
                        Arguments.of(3, 7),
                        Arguments.of(0, 10)
                );
            }

            @Test
            public void 프레임이_스트라이크이고_다음_프레임이_스트라이크가_아닐_때_다음_프레임이_끝나지_않았다면_점수를_계산할_수_없다() {
                //given
                Frame frame = prevFrame.bowl(10);    // 스트라이크
                Frame nextFrame = frame.bowl(5);
                //when
                prevFrame.calculateScore();
                int score = frame.calculateScore();
                //then
                assertThat(score).isEqualTo(NONE);
            }

            @ParameterizedTest
            @ValueSource(ints = {0, 1, 5, 10})
            public void 프레임이_스트라이크이고_다음_프레임이_스트라이크면_그_다음_프레임의_첫_점수를_합한값이_결과가_된다(int first) {
                //given
                Frame frame = prevFrame.bowl(10);    // 스트라이크
                Frame nextFrame = frame.bowl(10);
                Frame nextNextFrame = nextFrame.bowl(first);
                //when
                prevFrame.calculateScore();
                int score = frame.calculateScore();
                //then
                assertThat(score).isEqualTo(1 + 3 + 10 + 10 + first);
            }

            @Test
            public void 프레임이_스트라이크이고_다음_프레임이_스트라이크이면_그_다음_프레임이_시작하지_않았다면_점수를_계산할_수_없다() {
                //given
                Frame frame = prevFrame.bowl(10);    // 스트라이크
                Frame nextFrame = frame.bowl(10);
                //when
                prevFrame.calculateScore();
                int score = frame.calculateScore();
                //then
                assertThat(score).isEqualTo(NONE);
            }

        }

    }

}
