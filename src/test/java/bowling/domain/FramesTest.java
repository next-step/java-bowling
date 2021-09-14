package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FramesTest {

    private Frames frames = Frames.init();

    @BeforeEach
    void setUp() {
        frames = Frames.init();
    }

    @Test
    @DisplayName("init() 초기 프레임을 생성한다.")
    void initTest() {

    }

    @ParameterizedTest
    @DisplayName("현재 진행중인 이닝을 받는다.")
    @MethodSource("currentInningSource")
    void currentInningTest(Pitch pitch, Inning current) {
        frames.recodePitch(pitch);
        assertThat(frames.currentInning()).isEqualTo(current);
    }

    static Stream<Arguments> currentInningSource() {
        return Stream.of(
                Arguments.of(
                        new Pitch(3), new Inning(1)
                ),
                Arguments.of(
                        new Pitch(3), new Inning(1)
                ),
                Arguments.of(
                        new Pitch(10), new Inning(2)
                ),
                Arguments.of(
                        new Pitch(10), new Inning(2)
                ),
                Arguments.of(
                        new Pitch(5), new Inning(1)
                )
        );
    }

    @Test
    @DisplayName("10이닝에 strike, spare 가 아니라면 false.")
    void game_over_then_true() {
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(3));//10 이닝 1피칭
        frames.recodePitch(new Pitch(3));//10 이닝 2피칭

        assertThat(frames.nonOver()).isFalse();
    }

    @Test
    @DisplayName("이닝이 10 이닝 이하라면 true")
    void under_10_non_over_true() {
        assertAll(  
                () -> {
                    frames.recodePitch(new Pitch(10));
                    assertThat(frames.nonOver()).isTrue();
                },
                () -> {
                    frames.recodePitch(new Pitch(10));
                    assertThat(frames.nonOver()).isTrue();
                },
                () -> {
                    frames.recodePitch(new Pitch(10));
                    assertThat(frames.nonOver()).isTrue();
                },
                () -> {
                    frames.recodePitch(new Pitch(10));
                    assertThat(frames.nonOver()).isTrue();
                },
                () -> {
                    frames.recodePitch(new Pitch(10));
                    assertThat(frames.nonOver()).isTrue();
                },
                () -> {
                    frames.recodePitch(new Pitch(10));
                    assertThat(frames.nonOver()).isTrue();
                },
                () -> {
                    frames.recodePitch(new Pitch(10));
                    assertThat(frames.nonOver()).isTrue();
                },
                () -> {
                    frames.recodePitch(new Pitch(10));
                    assertThat(frames.nonOver()).isTrue();
                },
                () -> {
                    frames.recodePitch(new Pitch(10));
                    assertThat(frames.nonOver()).isTrue();
                },
                () -> {
                    frames.recodePitch(new Pitch(7));
                    assertThat(frames.nonOver()).isTrue();
                }
        );
    }

    @Test
    @DisplayName("마지막 이닝에서, 스트라이크를 쳤다면 1번 더 입력 받는다.")
    void last_inning_strike() {
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        //10 inning strike
        frames.recodePitch(new Pitch(10));

        assertThat(frames.nonOver()).isTrue();
    }

    @Test
    @DisplayName("마지막 이닝에서, 스페어를 쳤다면 1번 더 입력 받는다.")
    void last_inning_spare() {
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        frames.recodePitch(new Pitch(10));
        //10 inning spare
        frames.recodePitch(new Pitch(3));
        frames.recodePitch(new Pitch(7));

        assertThat(frames.nonOver()).isTrue();
    }
}