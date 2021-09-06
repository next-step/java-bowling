package bowling.domain.frame;

import static bowling.domain.Fixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FramesTest {
    Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.from(new ArrayList<>());
    }

    @DisplayName("iniFrames 메서드를 호출하면, 점수판을 그리기 위한 문자열 리스트 값을 초기화한다")
    @Test
    void initFramesTest() {
        assertThat(frames.results()).isInstanceOf(ArrayList.class);
    }

    @DisplayName("투구 동작을 구현한 메서드 throwBalls에 다양한 상황을 입력하면 그에 맞는 결과를 얻을 수 있다")
    @Test
    void throwBallsTest() {
        // frames.initFrames();
        assertAll(
            () -> {
               frames.throwBalls(Arrays.asList(0, 9));
               assertThat(frames.results().get(0)).isEqualTo("-|9");
            },
            () ->{
                frames.throwBalls(Arrays.asList(9, 0));
                assertThat(frames.results().get(1)).isEqualTo("9|-");
            },
            () ->{
                frames.throwBalls(Arrays.asList(1, 9));
                assertThat(frames.results().get(2)).isEqualTo("1|/");
            },
            () ->{
                frames.throwBalls(Arrays.asList(9, 1));
                assertThat(frames.results().get(3)).isEqualTo("9|/");
            },
            () ->{
                frames.throwBalls(Arrays.asList(0, 0));
                assertThat(frames.results().get(4)).isEqualTo("-|-");
            },
            () ->{
                frames.throwBalls(Collections.singletonList(10));
                assertThat(frames.results().get(5)).isEqualTo("X");
            },
            () ->{
                frames.throwBalls(Arrays.asList(4, 5));
                assertThat(frames.results().get(6)).isEqualTo("4|5");
            },
            () ->{
                frames.throwBalls(Arrays.asList(0, 10));
                assertThat(frames.results().get(7)).isEqualTo("-|/");
            },
            () ->{
                frames.throwBalls(Arrays.asList(4, 6));
                assertThat(frames.results().get(8)).isEqualTo("4|/");
            },
            () ->{
                frames.throwBalls(Arrays.asList(10, 10, 10));
                assertThat(frames.results().get(9)).isEqualTo("X|X|X");
            }
        );
    }

    @DisplayName("첫번째 프레임에서 5개의 핀을 각각 두번 쓰러뜨리면 다음 프레임으로 넘어간다")
    @Test
    void isNextTest() {
        frames.throwBalls(5);
        assertThat(frames.isNext()).isFalse();
        frames.throwBalls(5);
        assertThat(frames.isNext()).isTrue();
    }

    @DisplayName("첫번째 프레임에서 핀을 다 쓰러뜨리지 못해도 2번 투구하면 다음 프레임으로 넘어간다")
    @Test
    void isNextTest2() {
        frames.throwBalls(Arrays.asList(2, 3));
        assertThat(frames.isNext()).isTrue();
    }

    @DisplayName("첫번째 프레임에서 스트라이크를 치면 다음 프레임으로 넘어간다")
    @Test
    void isNextTest3() {
        frames.throwBalls(10);
        assertThat(frames.isNext()).isTrue();
    }

    @DisplayName("마지막 프레임에서 3번의 스트라이크를 치면 종료된다")
    @Test
    void isNextTest4() {
        assertAll(
            () -> {
                frames = Frames.from(NINE_STRIKE_STATE);
                frames.throwBalls(10);
                assertThat(frames.isNext()).isFalse();
            },
            () -> {
                frames.throwBalls(10);
                assertThat(frames.isNext()).isFalse();
            },
            () -> {
                frames.throwBalls(10);
                assertThat(frames.isNext()).isTrue();
            }
        );
    }

    @DisplayName("마지막 프레임에서 1번의 스페어 1번의 스트라이크를 치면 종료된다")
    @Test
    void isNextTest5() {
        assertAll(
            () -> {
                frames = Frames.from(NINE_STRIKE_STATE);
                frames.throwBalls(4);
                assertThat(frames.isNext()).isFalse();
            },
            () -> {
                frames.throwBalls(6);
                assertThat(frames.isNext()).isFalse();
            },
            () -> {
                frames.throwBalls(10);
                assertThat(frames.isNext()).isTrue();
            }
        );
    }

    @DisplayName("마지막 프레임에서, 스트라이크 없이 2번의 투구 이내 쓰러뜨리지 못하면 종료된다")
    @Test
    void isNextTest6() {
        assertAll(
            () -> {
                frames = Frames.from(NINE_STRIKE_STATE);
                frames.throwBalls(4);
                assertThat(frames.isNext()).isFalse();
            },
            () -> {
                frames.throwBalls(5);
                assertThat(frames.isNext()).isTrue();
            }
        );
    }

    @DisplayName("마지막 프레임에서, 2번의 스트라이크, 다음 투구에서 몇개를 치던 종료된다")
    @Test
    void isNextTest7() {
        assertAll(
            () -> {
                frames = Frames.from(NINE_STRIKE_STATE);
                frames.throwBalls(10);
                assertThat(frames.isNext()).isFalse();
            },
            () -> {
                frames.throwBalls(10);
                assertThat(frames.isNext()).isFalse();
            },
            () -> {
                frames.throwBalls(5);
                assertThat(frames.isNext()).isTrue();
            }
        );
    }
}
