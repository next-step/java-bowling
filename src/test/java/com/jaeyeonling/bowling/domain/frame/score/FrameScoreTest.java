package com.jaeyeonling.bowling.domain.frame.score;

import com.jaeyeonling.bowling.domain.count.ShorterThanMinCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class FrameScoreTest {

    @DisplayName("프레임의 점수 값이 " + FrameScore.MIN + " 보다 낮으면 예외처리한다.")
    @ParameterizedTest
    @ValueSource(ints = {FrameScore.MIN - 1})
    void throwShorterThanMinFrameScoreException(final int frameScore) {
        assertThatExceptionOfType(ShorterThanMinFrameScoreException.class)
                .isThrownBy(() -> FrameScore.of(frameScore));
    }

    @DisplayName("점수 값을 더한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 6, 7, 10})
    void sum(final int rawFrameScore) {
        // given
        final FrameScore frameScore = FrameScore.of(rawFrameScore);

        // when
        final FrameScore sumFrameScore = frameScore.sum(frameScore);

        // then
        assertThat(sumFrameScore.getScore()).isEqualTo(rawFrameScore * 2);
    }

    @DisplayName("스트라이크의 점수 값을 계산한다.")
    @Test
    void strikeCalculateScore() {
        // given
        FrameScore frameScore = FrameScore.STRIKE;

        // when
        frameScore = frameScore.calculate(FrameScore.STRIKE);
        frameScore = frameScore.calculate(FrameScore.STRIKE);

        // then
        assertThat(frameScore.getScore()).isEqualTo(30);
    }

    @DisplayName("스페어의 점수 값을 계산한다.")
    @Test
    void spareCalculateScore() {
        // given
        FrameScore frameScore = FrameScore.STRIKE;

        // when
        frameScore = frameScore.calculate(FrameScore.SPARE);

        // then
        assertThat(frameScore.getScore()).isEqualTo(20);
    }

    @DisplayName("스트라이크의 점수 값을 계산 후 완료를 확인한다.")
    @Test
    void strikeCalculateScoreComplete() {
        // given
        FrameScore frameScore = FrameScore.STRIKE;

        // when
        frameScore = frameScore.calculate(FrameScore.STRIKE);
        frameScore = frameScore.calculate(FrameScore.STRIKE);

        // then
        assertThat(frameScore.isComplete()).isTrue();
    }

    @DisplayName("스페어의 점수 값을 계산 후 완료를 확인한다.")
    @Test
    void spareCalculateScoreComplete() {
        // given
        FrameScore frameScore = FrameScore.SPARE;

        // when
        frameScore = frameScore.calculate(FrameScore.SPARE);

        // then
        assertThat(frameScore.isComplete()).isTrue();
    }

    @DisplayName("점수를 계산할 수 있는 횟수가 없는 상태에서 계산 시 예외처리한다.")
    @Test
    void throwShorterThanMinCountException() {
        // when / then
        assertThatExceptionOfType(ShorterThanMinCountException.class)
                .isThrownBy(() -> FrameScore.GUTTER.calculate(FrameScore.GUTTER));
    }
}
