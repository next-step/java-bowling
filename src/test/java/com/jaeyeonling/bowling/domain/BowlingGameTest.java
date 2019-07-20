package com.jaeyeonling.bowling.domain;

import com.jaeyeonling.bowling.domain.frame.Frames;
import com.jaeyeonling.bowling.domain.pins.KnockdownPins;
import com.jaeyeonling.bowling.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = BowlingGame.with(User.of("TST"));
    }

    @DisplayName("기본 인덱스는 1이다.")
    @Test
    void defaultFrameIndex() {
        // when
        final int currentIndex = bowlingGame.getCurrentFrameIndex();

        // then
        assertThat(currentIndex).isEqualTo(1);
    }

    @DisplayName("스트라이크를 던지면 마지막 프레임 전 까지 1씩 증가한다.")
    @Test
    void strikeIncrementFrameIndex() {
        for (int i = 1; i <= Frames.NORMAL_FRAME_COUNT; i++) {
            // when
            bowlingGame.bowl(KnockdownPins.MAX);
            final int currentIndex = bowlingGame.getCurrentFrameIndex();

            // then
            assertThat(currentIndex).isEqualTo(currentIndex);
        }
    }

    @DisplayName("미스를 2번 던지면 마지막 프레임 전 까지 1씩 증가한다.")
    @Test
    void missIncrementFrameIndex() {
        for (int i = 1; i <= Frames.NORMAL_FRAME_COUNT; i++) {
            // when
            bowlingGame.bowl(KnockdownPins.valueOf(1));
            bowlingGame.bowl(KnockdownPins.valueOf(1));

            final int currentIndex = bowlingGame.getCurrentFrameIndex();

            // then
            assertThat(currentIndex).isEqualTo(currentIndex);
        }
    }

    @DisplayName("마지막 프레임이 끝나기 전 까지 게임이 가능하다.")
    @Test
    void canPlay() {
        for (int i = 0; i < 20; i++) {
            // when
            final boolean canPlay = bowlingGame.canPlay();
            bowlingGame.bowl(KnockdownPins.valueOf(1));

            // then
            assertThat(canPlay).isTrue();
        }
    }

    @DisplayName("미스 20번을 던지면 더이상 게임을 할 수 없다.")
    @Test
    void cannotPlay() {
        // given
        for (int i = 0; i < 20; i++) {
            bowlingGame.bowl(KnockdownPins.valueOf(1));
        }

        // when
        final boolean canPlay = bowlingGame.canPlay();

        // then
        assertThat(canPlay).isFalse();
    }
}
