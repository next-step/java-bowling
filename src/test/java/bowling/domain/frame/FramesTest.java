package bowling.domain.frame;

import bowling.domain.score.Score;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FramesTest {
    Frames frames;

    private static Stream<Arguments> throwBallsTest() {
        return Stream.of(
            Arguments.of(1, Arrays.asList(0, 9), "-|9"),
            Arguments.of(2, Arrays.asList(9, 0), "9|-"),
            Arguments.of(3, Arrays.asList(1, 9), "1|/"),
            Arguments.of(4, Arrays.asList(9, 1), "9|/"),
            Arguments.of(5, Arrays.asList(1, 9), "1|/"),
            Arguments.of(6, Arrays.asList(0, 0), "-|-"),
            Arguments.of(7, Collections.singletonList(10), "X"),
            Arguments.of(8, Arrays.asList(4, 5), "4|5"),
            Arguments.of(9, Arrays.asList(0, 10), "-|/"),

            Arguments.of(10, Collections.singletonList(10), "X"),
            Arguments.of(10, Arrays.asList(10, 10), "X|X"),
            Arguments.of(10, Arrays.asList(4, 6), "4|/"),
            Arguments.of(10, Arrays.asList(4, 6, 10), "4|/|X"),
            Arguments.of(10, Arrays.asList(10, 4, 6), "X|4|/"),

            Arguments.of(10, Arrays.asList(10, 10, 0), "X|X|-"),
            Arguments.of(10, Arrays.asList(10, 0, 10), "X|-|/"),
            Arguments.of(10, Arrays.asList(10, 0, 0), "X|-|-"),
            Arguments.of(10, Arrays.asList(0, 10, 0), "-|/|-"),

            Arguments.of(10, Arrays.asList(0, 10, 6), "-|/|6")
        );
    }

    @BeforeAll
    void setUp() {
        frames = new Frames();
    }

    @DisplayName("iniFrames 메서드를 호출하면, 점수판을 그리기 위한 문자열 리스트 값을 초기화한다")
    @Test
    void initFramesTest() {
        frames.initFrames();
        assertThat(frames.results()).containsExactly("", "", "", "", "", "", "", "", "", "");
    }

    @DisplayName("투구 동작을 구현한 메서드 throwBalls에 다양한 상황을 입력하면 그에 맞는 결과를 얻을 수 있다")
    @ParameterizedTest
    @MethodSource
    void throwBallsTest(int frameNumber, List<Integer> scores, String expected) {
        frames.initFrames();
        frames.throwBalls(frameNumber, scores);
        assertThat(frames.results().get(frameNumber - 1)).isEqualTo(expected);
    }

    @DisplayName("첫번째 프레임에서 5개의 핀을 각각 두번 쓰러뜨리면 다음 프레임으로 넘어간다")
    @Test
    void isNextTest() {
        frames.initFrames();
        frames.throwBall(1, Score.from(5));
        assertThat(frames.isNext(1)).isFalse();
        frames.throwBall(1, Score.from(5));
        assertThat(frames.isNext(1)).isTrue();
    }

    @DisplayName("첫번째 프레임에서 핀을 다 쓰러뜨리지 못해도 2번 투구하면 다음 프레임으로 넘어간다")
    @Test
    void isNextTest2() {
        frames.initFrames();
        frames.throwBall(1, Score.from(2));
        frames.throwBall(1, Score.from(3));
        assertThat(frames.isNext(1)).isTrue();
    }

    @DisplayName("첫번째 프레임에서 스트라이크를 치면 다음 프레임으로 넘어간다")
    @Test
    void isNextTest3() {
        frames.initFrames();
        frames.throwBall(1, Score.from(10));
        assertThat(frames.isNext(1)).isTrue();
    }

    @DisplayName("마지막 프레임에서 3번의 스트라이크를 치면 종료된다")
    @Test
    void isNextTest4() {
        frames.initFrames();
        frames.throwBall(10, Score.from(10));
        assertThat(frames.isNext(10)).isFalse();
        frames.throwBall(10, Score.from(10));
        assertThat(frames.isNext(10)).isFalse();
        frames.throwBall(10, Score.from(10));
        assertThat(frames.isNext(10)).isTrue();
    }

    @DisplayName("마지막 프레임에서 1번의 스페어 1번의 스트라이크를 치면 종료된다")
    @Test
    void isNextTest5() {
        frames.initFrames();
        frames.throwBall(10, Score.from(4));
        assertThat(frames.isNext(10)).isFalse();
        frames.throwBall(10, Score.from(6));
        assertThat(frames.isNext(10)).isFalse();
        frames.throwBall(10, Score.from(10));
        assertThat(frames.isNext(10)).isTrue();
    }

    @DisplayName("마지막 프레임에서, 스트라이크 없이 2번의 투구 이내 쓰러뜨리지 못하면 종료된다")
    @Test
    void isNextTest6() {
        frames.initFrames();
        frames.throwBall(10, Score.from(4));
        assertThat(frames.isNext(10)).isFalse();
        frames.throwBall(10, Score.from(5));
        assertThat(frames.isNext(10)).isTrue();
    }

    @DisplayName("마지막 프레임에서, 2번의 스트라이크, 다음 투구에서 몇개를 치던 종료된다")
    @Test
    void isNextTest7() {
        frames.initFrames();
        frames.throwBall(10, Score.from(10));
        assertThat(frames.isNext(10)).isFalse();
        frames.throwBall(10, Score.from(10));
        assertThat(frames.isNext(10)).isFalse();
        frames.throwBall(10, Score.from(5));
        assertThat(frames.isNext(10)).isTrue();
    }
}
