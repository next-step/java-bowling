package bowling.domain;

import bowling.domain.frame.DefaultFrame;
import bowling.domain.score.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DefaultFrameTest {
    @DisplayName("첫번째 프레임 생성")
    @Test
    void createFrame() {
        assertThatCode(() -> DefaultFrame.first()).doesNotThrowAnyException();
    }

    @DisplayName("다음 프레임 생성")
    @Test
    void createNextFrame() {
        DefaultFrame first = DefaultFrame.first();
        first.addScore(5);
        first.addScore(4);
        assertThatCode(() -> first.nextFrame(0)).doesNotThrowAnyException();
    }

    @DisplayName("마지막 프레임 생성")
    @Test
    void createLastFrame() {
        DefaultFrame first = DefaultFrame.first();
        first.addScore(5);
        first.addScore(4);
        assertThatCode(() -> first.lastFrame(0)).doesNotThrowAnyException();
    }

    @DisplayName("점수 추가")
    @ParameterizedTest
    @MethodSource("points")
    void createNextFrame(List<Integer> values) {
        DefaultFrame defaultFrame = DefaultFrame.first();

        assertThatCode(
                () -> {
                    for (Integer value : values) {
                        defaultFrame.addScore(value);
                    }
                }
        );
    }

    static Stream<Arguments> points() {
        return Stream.of(
                arguments(Arrays.asList(1, 2)),
                arguments(Arrays.asList(4, 5))
        );
    }

    @DisplayName("볼링공을 던질수 있는 상태")
    @Test
    void isPlayable() {
        DefaultFrame defaultFrame = DefaultFrame.first();
        defaultFrame.addScore(1);
        assertThat(defaultFrame.isPlayable()).isTrue();
    }

    @DisplayName("볼링공을 던질수 없는 상태 - 초구 스트라이크")
    @Test
    void isNotPlayableByStrike() {
        DefaultFrame defaultFrame = DefaultFrame.first();
        defaultFrame.addScore(10);
        assertThat(defaultFrame.isPlayable()).isFalse();
    }

    @DisplayName("볼링공을 던질수 없는 상태 - 2번의 공을 던졌을때")
    @Test
    void isNotPlayableByCount() {
        DefaultFrame defaultFrame = DefaultFrame.first();
        defaultFrame.addScore(1);
        defaultFrame.addScore(2);
        assertThat(defaultFrame.isPlayable()).isFalse();
    }
}