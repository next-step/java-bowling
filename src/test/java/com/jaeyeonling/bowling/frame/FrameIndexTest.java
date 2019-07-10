package com.jaeyeonling.bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FrameIndexTest {

    @DisplayName("프레임 인덱스 값이 " + FrameIndex.MAX + "보다 크면 예외처리 한다.")
    @ParameterizedTest
    @ValueSource(ints = {FrameIndex.MAX + 1})
    void throwLongerThanMaxFrameIndexException(final int frameIndex) {
        assertThatExceptionOfType(LongerThanMaxFrameIndexException.class)
                .isThrownBy(() -> FrameIndex.valueOf(frameIndex));
    }

    @DisplayName("프레임 인덱스 값이 " + FrameIndex.MIN + "보다 낮으면 예외처리 한다.")
    @ParameterizedTest
    @ValueSource(ints = {FrameIndex.MIN - 1})
    void throwShorterThanMinFrameIndexException(final int frameIndex) {
        assertThatExceptionOfType(ShorterThanMinFrameIndexException.class)
                .isThrownBy(() -> FrameIndex.valueOf(frameIndex));
    }

    @DisplayName("프레임 인덱스 값이 같다면 같은 객체다.")
    @ParameterizedTest
    @ValueSource(ints = {FrameIndex.MIN, 5, FrameIndex.MAX})
    void equals(final int rawFrameIndex) {
        // given
        final FrameIndex frameIndex = FrameIndex.valueOf(rawFrameIndex);
        final FrameIndex expected = FrameIndex.valueOf(rawFrameIndex);

        // when
        final boolean isEquals = frameIndex.equals(expected);

        // then
        assertThat(isEquals).isTrue();
    }

    @DisplayName("프레임 인덱스 첫 값은 " + FrameIndex.MIN + "이다.")
    @ParameterizedTest
    @ValueSource(ints = {FrameIndex.MIN})
    void first(final int rawFrameIndex) {
        // given
        final FrameIndex first = FrameIndex.first();
        final FrameIndex expected = FrameIndex.valueOf(rawFrameIndex);

        // when
        final boolean isEquals = first.equals(expected);

        // then
        assertThat(isEquals).isTrue();
    }

    @DisplayName("프레임 인덱스 마지막 값은 " + FrameIndex.MAX + "이다.")
    @ParameterizedTest
    @ValueSource(ints = {FrameIndex.MAX})
    void last(final int rawFrameIndex) {
        // given
        final FrameIndex last = FrameIndex.last();
        final FrameIndex expected = FrameIndex.valueOf(rawFrameIndex);

        // when
        final boolean isEquals = last.equals(expected);

        // then
        assertThat(isEquals).isTrue();
    }

    @DisplayName("프레임 인덱스 다음 값은 " + FrameIndex.INCREMENT_VALUE + "를 더한 값이다.")
    @ParameterizedTest
    @ValueSource(ints = {FrameIndex.MIN, 5})
    void next(final int rawFrameIndex) {
        // given
        final FrameIndex frameIndex = FrameIndex.valueOf(rawFrameIndex);
        final FrameIndex expected = FrameIndex.valueOf(rawFrameIndex + FrameIndex.INCREMENT_VALUE);

        // when
        final FrameIndex next = frameIndex.next();

        // then
        assertThat(next).isEqualTo(expected);
    }
}
