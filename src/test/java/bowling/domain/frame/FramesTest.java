package bowling.domain.frame;

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
                        Pitch.from(3), Inning.from(1)
                ),
                Arguments.of(
                        Pitch.from(3), Inning.from(1)
                ),
                Arguments.of(
                        Pitch.from(10), Inning.from(2)
                ),
                Arguments.of(
                        Pitch.from(10), Inning.from(2)
                ),
                Arguments.of(
                        Pitch.from(5), Inning.from(1)
                )
        );
    }

    @Test
    @DisplayName("10이닝에 strike, spare 가 아니라면 over is true.")
    void game_over_then_true() {
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));

        frames.recodePitch(Pitch.from(3));//10 이닝 1피칭
        frames.recodePitch(Pitch.from(3));//10 이닝 2피칭

        assertThat(frames.over()).isTrue();
    }

    @Test
    @DisplayName("이닝이 10 이닝 이하라면 over is false")
    void under_10_non_over_true() {
        assertAll(  
                () -> {
                    frames.recodePitch(Pitch.from(10));
                    assertThat(frames.over()).isFalse();
                },
                () -> {
                    frames.recodePitch(Pitch.from(10));
                    assertThat(frames.over()).isFalse();
                },
                () -> {
                    frames.recodePitch(Pitch.from(10));
                    assertThat(frames.over()).isFalse();
                },
                () -> {
                    frames.recodePitch(Pitch.from(10));
                    assertThat(frames.over()).isFalse();
                },
                () -> {
                    frames.recodePitch(Pitch.from(10));
                    assertThat(frames.over()).isFalse();
                },
                () -> {
                    frames.recodePitch(Pitch.from(10));
                    assertThat(frames.over()).isFalse();
                },
                () -> {
                    frames.recodePitch(Pitch.from(10));
                    assertThat(frames.over()).isFalse();
                },
                () -> {
                    frames.recodePitch(Pitch.from(10));
                    assertThat(frames.over()).isFalse();
                },
                () -> {
                    frames.recodePitch(Pitch.from(10));
                    assertThat(frames.over()).isFalse();
                },
                () -> {
                    frames.recodePitch(Pitch.from(7));
                    assertThat(frames.over()).isFalse();
                }
        );
    }

    @Test
    @DisplayName("마지막 이닝에서, 스트라이크를 쳤다면, over is false.")
    void last_inning_strike() {
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));

        //10 inning strike
        frames.recodePitch(Pitch.from(10));

        assertThat(frames.over()).isFalse();
    }

    @Test
    @DisplayName("마지막 이닝에서, 스페어를 쳤다면, over is false.")
    void last_inning_spare() {
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        frames.recodePitch(Pitch.from(10));
        //10 inning spare
        frames.recodePitch(Pitch.from(3));
        frames.recodePitch(Pitch.from(7));

        assertThat(frames.over()).isFalse();
    }
}